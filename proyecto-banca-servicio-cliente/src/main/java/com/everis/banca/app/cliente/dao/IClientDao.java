package com.everis.banca.app.cliente.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.banca.app.cliente.models.documents.Client;

public interface IClientDao extends ReactiveMongoRepository<Client, String> {
	
}
