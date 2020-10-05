package com.luminas.facturacion.service;

import com.luminas.facturacion.model.Factura;
import com.luminas.facturacion.model.Pedido;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FacturaService {
    ResponseEntity<List<Factura>> generadorDeFacturas(Pedido[] pedidos);

    ResponseEntity<long[]> eliminarPedidosDesdeFacturas(Factura[] facturas);
}
