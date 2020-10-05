package com.luminas.facturacion.service;

import com.luminas.facturacion.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Service("facturaServiceImpl")
public class FacturaServiceImpl implements FacturaService {

    private static final String OPERACION_CREDITO = "crédito";
    private static final String OPERACION_FACTURA = "facturación";

    @Override
    public ResponseEntity<List<Factura>> generadorDeFacturas(Pedido[] pedidos){

        List<Factura> facturas = new ArrayList<>();
        try {
            Arrays.stream(pedidos).forEach(pedido -> {
                System.out.println(Thread.currentThread().getId());
                Factura factura = new Factura();

                Cabecera cabecera = this.configurarCabecera(pedido.getCliente());
                HashMap<String,Double> relacionesPrecioProducto = this.calcularPrecioProducto(pedido);
                double precioNeto = this.calcularPrecioNeto(relacionesPrecioProducto, pedido.getDetalles());
                float iva = this.calcularIva(cabecera.getLetra());
                double precioVenta = this.calcularPrecioDeVenta(iva,precioNeto);
                double montoIva = precioVenta - precioNeto;


                factura.setCabeceraFactura(cabecera);
                factura.setCantidad(pedido.getDetalles().length);
                factura.setIva(iva);
                factura.setProductoPrecioUnitario(relacionesPrecioProducto);
                factura.setPrecioNeto(precioNeto);
                factura.setPrecioVenta(precioVenta);
                factura.setMontoIva(montoIva);

                Pie pie = new Pie();
                pie.setTotal(factura.getPrecioVenta());
                pie.setTotalIva(montoIva);
                factura.setPieFactura(pie);

                facturas.add(factura);

                this.escribirEnArchivo(factura.getCabeceraFactura(), factura.getPrecioVenta(), this.OPERACION_FACTURA);
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

                this.escribirEnArchivo(notaCredito.getCabeceraNotaCredito(),  factura.getPrecioVenta(),this.OPERACION_CREDITO);

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

    private HashMap<String,Double> calcularPrecioProducto(Pedido pedido){
        HashMap<String,Double> precioProducto = new HashMap<>();
        Producto[] productos = pedido.getDetalles();
        for(Producto producto : productos){
            precioProducto.put(producto.getNombre(),producto.getPrecio());
        }
        return precioProducto;
    }

    private double calcularPrecioNeto(HashMap<String,Double> relacionesPrecioProducto,Producto[] productos){
        double precioNeto = 0;
        for(String nombre : relacionesPrecioProducto.keySet()){
            long cantidad = Arrays.stream(productos).filter(producto -> nombre == producto.getNombre()).count();
            precioNeto += cantidad * relacionesPrecioProducto.get(nombre);
        }
        return precioNeto;
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

    private void escribirEnArchivo(Cabecera cabecera, double total, String tipoDeOperación) {
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
