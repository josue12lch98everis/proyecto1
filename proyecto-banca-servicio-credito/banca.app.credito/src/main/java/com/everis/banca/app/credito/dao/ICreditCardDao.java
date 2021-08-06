package com.everis.banca.app.credito.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

import com.everis.banca.app.credito.documents.CreditCard;

public interface ICreditCardDao extends ReactiveMongoRepository<CreditCard,String> {

    Flux<CreditCard> findByClientId(String clientId);

}
