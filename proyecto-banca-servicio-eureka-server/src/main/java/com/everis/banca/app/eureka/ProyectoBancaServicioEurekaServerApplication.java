package com.everis.banca.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ProyectoBancaServicioEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBancaServicioEurekaServerApplication.class, args);
	}

}
