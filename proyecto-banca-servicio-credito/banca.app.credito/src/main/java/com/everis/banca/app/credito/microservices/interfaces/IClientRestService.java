package com.everis.banca.app.credito.microservices.interfaces;

import com.everis.banca.app.credito.models.ClientModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientRestService {

    Flux<ClientModel> findAll();

	Mono<ClientModel> findById(String id);

    
}
