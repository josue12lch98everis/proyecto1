package com.everis.banca.app.cliente.controllers;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.banca.app.cliente.dao.IClientDao;
import com.everis.banca.app.cliente.models.documents.Client;
import com.everis.banca.app.cliente.service.IClientService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Client")
@Slf4j
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping()
	public Mono<ResponseEntity<Flux<Client>>> getAllClient() {
		
		return Mono
				.just(ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(clientService.findAll())
				);
	}

	@GetMapping( value = "/{id}")
	public Mono<ResponseEntity<Client>> findById(@PathVariable("id") String id) {
		log.info("Cliente ID: " + id);
		return clientService.findById(id)
				.map(p -> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	public Mono<ResponseEntity<Client>> saveClient(@RequestBody Client client) {
		if (client.getCreateAt() == null) {
			client.setCreateAt(new Date());
		}
		return clientService.create(client).map(p -> ResponseEntity
				.created(URI.create("/client/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)

		);
	}

	@PutMapping(value = "/{id}")
	public Mono<ResponseEntity<Client>> update(@RequestBody Client client, @PathVariable(value = "id") String id) {

		if (client.getCreateAt() == null) {
			client.setCreateAt(new Date());
		}
		log.info("id: ", id);
		return clientService.update(id, client)
				.map(p -> ResponseEntity
						.created(URI.create("/client/".concat(p.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(p)
				)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") String id) {

		log.info("id: " + id);
		return clientService.findById(id)
				.flatMap(p -> {
					return clientService.delete(p)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

	}
}
