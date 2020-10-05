package com.luminas.facturacion.controller;

import com.luminas.facturacion.model.Factura;
import com.luminas.facturacion.model.Pedido;
import com.luminas.facturacion.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController()
@RequestMapping("services")
public class FacturaController {

    @Autowired
    @Qualifier("facturaServiceImpl")
    FacturaService facturaService;
    
    @PostMapping(path="/v1/facturas")
    @Async
    public CompletableFuture<ResponseEntity<List<Factura>>> altaDeFacturas(@RequestHeader Map<String, Object> headers,
                                                                          @RequestBody Pedido[] body ) {
        return CompletableFuture.completedFuture(facturaService.generadorDeFacturas(body));
    }

    @PostMapping(path="/v1/facturas/paraEliminar")
    @Async
    public CompletableFuture<ResponseEntity<long[]>> bajaDePedidos(@RequestHeader Map<String, Object> headers,
                                                           @RequestBody Factura[] body ){
        return CompletableFuture.completedFuture(facturaService.eliminarPedidosDesdeFacturas(body));
    }

    @GetMapping(path="/v1/archivoDeTransacciones",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> consultarArchivoDeTransacciones() throws FileNotFoundException {
        InputStreamResource resource = new InputStreamResource(new FileInputStream("transactions.txt"));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
