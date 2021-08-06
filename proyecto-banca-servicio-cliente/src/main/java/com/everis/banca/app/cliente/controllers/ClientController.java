package com.everis.banca.app.cliente.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.banca.app.cliente.dao.ClientDao;
import com.everis.banca.app.cliente.models.documents.Client;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Client")
@Slf4j
public class ClientController {

	@Autowired
	private ClientDao clientDao;
	
	@GetMapping()
	public Flux<Client> getAllClient() {
		
		Flux<Client> clients = clientDao.findAll().map(client -> {
			return client;
		});
		return clients;
	}

	@GetMapping( value = "/{id}")
	public Mono<Client> findById(@PathVariable("id") String id) {
		return clientDao.findById(id);
	}
	
	@PostMapping()
	public Mono<Client> saveClient(@RequestBody Client client) {
		try {
			client.setCreateAt(new Date());
			Mono<Client> save = clientDao.save(client);
			log.info("Se ingresó correctamente");
			return save;
		} catch (Exception e) {
			log.error("Error: " + e);
			return null;
		}
	}
}
