package com.everis.banca.app.credito.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.banca.app.credito.dao.ICreditCardDao;
import com.everis.banca.app.credito.documents.CreditCard;
import com.everis.banca.app.credito.services.interfaces.ICreditCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardImpl implements ICreditCardService {

	@Autowired
	private ICreditCardDao creditCardDao;
	
	@Override
	public Flux<CreditCard> getAllCreditCard() {
		// TODO Auto-generated method stub
		Flux<CreditCard> lcreditCard = creditCardDao.findAll();
		return lcreditCard;
	}

	@Override
	public Mono<CreditCard> createCreditCard(CreditCard creditCard) {
		if(creditCard.getId() != null) {
			return Mono.error(new IllegalArgumentException("Id of New CreditCard be null"));
		}
		return creditCardDao.save(creditCard);
	}

}
