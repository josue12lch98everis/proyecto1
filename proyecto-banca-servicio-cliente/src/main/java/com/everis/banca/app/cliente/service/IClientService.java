package com.everis.banca.app.cliente.service;

import com.everis.banca.app.cliente.models.documents.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {

	Flux<Client> findAll();

	Mono<Client> findById(String id);

	Mono<Client> create(Client client);

	Mono<Client> update(String id, Client client);

	Mono<Void> delete(Client client);

}
