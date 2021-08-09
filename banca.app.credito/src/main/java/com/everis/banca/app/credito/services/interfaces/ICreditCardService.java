package com.everis.banca.app.credito.services.interfaces;

import com.everis.banca.app.credito.documents.CreditCard;

import java.util.Map;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditCardService {

	Flux<CreditCard> findAll();

	Mono<CreditCard> findById(String id);

	Mono<CreditCard> create(CreditCard creditCard);

	Mono<Map<String, Object>> createMap(CreditCard creditCard);

	Flux<CreditCard> findByClientId(String clientId);
}
