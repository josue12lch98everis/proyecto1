package com.everis.banca.app.credito.microservices.interfaces;

import com.everis.banca.app.credito.models.ClientModel;

import reactor.core.publisher.Mono;

public interface IClientRestService {

    Mono<ClientModel> findById(String id);
}
