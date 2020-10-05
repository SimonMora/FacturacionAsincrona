package com.luminas.facturacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FacturacionAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturacionAsyncApplication.class, args);
	}

}
