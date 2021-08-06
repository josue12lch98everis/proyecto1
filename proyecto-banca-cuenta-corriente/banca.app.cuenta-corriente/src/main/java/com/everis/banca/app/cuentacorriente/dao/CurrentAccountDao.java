package com.everis.banca.app.cuentacorriente.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


import com.everis.banca.app.cuentacorriente.models.documents.CurrentAccount;

public interface CurrentAccountDao extends ReactiveMongoRepository<CurrentAccount, String> {

}
