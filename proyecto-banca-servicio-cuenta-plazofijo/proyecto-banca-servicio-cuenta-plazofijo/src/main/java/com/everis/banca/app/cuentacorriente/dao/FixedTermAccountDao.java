package com.everis.banca.app.cuentacorriente.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


// import com.everis.banca.app.cuentacorriente.models.documents.FixedTermAccount;

public interface FixedTermAccountDao extends ReactiveMongoRepository<Object, String> {

}
