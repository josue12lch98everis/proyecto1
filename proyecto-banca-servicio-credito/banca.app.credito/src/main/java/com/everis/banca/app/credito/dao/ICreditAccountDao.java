package com.everis.banca.app.credito.dao;
import org.springframework.data.mongodb.core.mapping.event.ReactiveAfterConvertCallback;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.banca.app.credito.documents.CreditAccount;

public interface ICreditAccountDao extends ReactiveMongoRepository<CreditAccount,String> {

}
