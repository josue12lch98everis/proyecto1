package com.everis.banca.app.credito.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.banca.app.credito.documents.CreditCard;

public interface ICreditCardDao extends ReactiveMongoRepository<CreditCard,String> {

}
