package com.everis.banca.app.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class ProyectoBancaServicioClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBancaServicioClienteApplication.class, args);
	}

}
