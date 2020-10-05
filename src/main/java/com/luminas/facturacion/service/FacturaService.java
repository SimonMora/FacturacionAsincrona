package com.luminas.facturacion.service;

import com.luminas.facturacion.model.Factura;
import com.luminas.facturacion.model.Pedido;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacturaService {
    ResponseEntity<List<Factura>> generadorDeFacturas(Pedido[] pedidos);

    ResponseEntity<long[]> eliminarPedidosDesdeFacturas(Factura[] facturas);
}
