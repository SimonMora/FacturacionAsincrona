package com.luminas.facturacion.service;

import com.luminas.facturacion.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Service("facturaServiceImpl")
public class FacturaServiceImpl implements FacturaService {

    @Override
    public ResponseEntity<List<Factura>> generadorDeFacturas(Pedido[] pedidos){
        HashMap<String,Float> ivaValues = new HashMap<String, Float>();
        ivaValues.put("A", 10.05f);
        ivaValues.put("B",21f);
        ivaValues.put("X",70f);
        List<Factura> facturas = new ArrayList<>();
        try {
            Arrays.stream(pedidos).forEach(pedido -> {
                System.out.println(Thread.currentThread().getId());
                Factura factura = new Factura();
                factura.setCabeceraFactura(this.configurarCabecera(pedido.getCliente()));

                Producto[] productos = pedido.getDetalles();

                Producto productoActual = Arrays.stream(pedido.getDetalles()).findFirst().get();
                long cantidad = Arrays.stream(pedido.getDetalles()).filter(productoLista -> productoActual.getCodigo() == productoLista.getCodigo()).count();
                float iva = this.calcularIva(factura.getCabeceraFactura().getLetra());
                double precioUnitario = productoActual.getPrecio();


                factura.setProducto(productoActual);
                factura.setPrecioUnitario(precioUnitario);
                factura.setCantidad(cantidad);
                factura.setIva(iva);

                factura.setPrecioNeto(this.calcularPrecioNeto(cantidad,precioUnitario));
                factura.setPrecioVenta(this.calcularPrecioDeVenta(iva,this.calcularPrecioNeto(cantidad,precioUnitario)));

                double montoIva = factura.getPrecioVenta() - factura.getPrecioNeto();
                factura.setMontoIva(montoIva);

                Pie pie = new Pie();
                pie.setTotal(factura.getPrecioVenta());
                pie.setTotalIva(montoIva);
                factura.setPieFactura(pie);

                facturas.add(factura);

                this.escribirEnArchivo(factura.getCabeceraFactura(), "facturación",factura.getPrecioVenta());

            });
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new ResponseEntity(facturas,HttpStatus.CONFLICT);
        }

        return new ResponseEntity(facturas, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<long[]> eliminarPedidosDesdeFacturas(Factura[] facturas) {
        try {
            Arrays.stream(facturas).forEach(factura -> {
                NotaCredito notaCredito = new NotaCredito();
                notaCredito.setCabeceraNotaCredito(configurarCabecera(factura.getCabeceraFactura().getCliente()));

                Pie pie = new Pie();
                pie.setTotal(factura.getPrecioVenta());


                this.escribirEnArchivo(notaCredito.getCabeceraNotaCredito(), "crédito", factura.getPrecioVenta());

            });
        }catch (Exception e){

        }
        return new ResponseEntity(new long[0], HttpStatus.OK);
    }

    private float calcularIva(String identificador){
        if("A".equals(identificador)) {
            return IvaValues.A.value;
        } else {
            return "B".equals(identificador) ? IvaValues.B.value : IvaValues.X.value;
        }
    }

    private double calcularPrecioNeto(long cantidad,double precioUnitario){
        return cantidad * precioUnitario;
    }

    private double calcularPrecioDeVenta(float iva, double precioNeto){
        return (precioNeto * iva / 100) + precioNeto;
    }

    private Cabecera configurarCabecera(Cliente cliente){

        HashMap<String,String> condicionImpositiva = new HashMap<>();
        condicionImpositiva.put("IVA Responsable Inscripto","A");
        condicionImpositiva.put("Monotributo","B");
        condicionImpositiva.put("IVA no Responsable","X");

        Cabecera cabecera = new Cabecera();
        cabecera.setFecha(LocalDate.now());
        cabecera.setCliente(cliente);
        cabecera.setLetra(condicionImpositiva.get(cliente.getCondicionImpositiva()));
        cabecera.setNumero(ThreadLocalRandom.current().nextLong(70000));
        cabecera.setTalon(ThreadLocalRandom.current().nextLong(70000000));

        return cabecera;
    }

    private void escribirEnArchivo(Cabecera cabecera, String tipoDeOperación, double total) {
        try{

            Date date = new Date();
            FileWriter fileWriter = new FileWriter("transactions.txt",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter
                    .printf("Tipo: %s, Client: %s, Tipo de documento: %s, Letra: %S, Nro: %d, Fecha: %s, Monto: %f $",
                            tipoDeOperación,
                            cabecera.getCliente().getId(),
                            cabecera.getCliente().getTipoDocumento(),
                            cabecera.getLetra(),
                            cabecera.getNumero(),
                            date.toString(),
                            total
                            );
            printWriter.println();
            printWriter.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
