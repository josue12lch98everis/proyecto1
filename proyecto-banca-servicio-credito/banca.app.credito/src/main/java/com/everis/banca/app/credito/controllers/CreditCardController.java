package com.everis.banca.app.credito.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.banca.app.credito.dao.ICreditCardDao;
import com.everis.banca.app.credito.documents.CreditCard;
import com.everis.banca.app.credito.microservices.interfaces.IClientRestService;
import com.everis.banca.app.credito.models.ClientModel;
import com.everis.banca.app.credito.services.interfaces.ICreditCardService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/CreditCard")
@Slf4j
public class CreditCardController {

	@Autowired
	private ICreditCardService creditCardDao;

	@Autowired(required = true)
	private IClientRestService clientRestService;
	
	@GetMapping()
	public ResponseEntity<Flux<CreditCard>> getAllCreditCard() {
		Flux<CreditCard> creditCards = creditCardDao.getAllCreditCard();
		return ResponseEntity.ok(creditCards);
	}
	
	@PostMapping()
	public ResponseEntity<Mono<CreditCard>> saveCreditCard(@RequestBody CreditCard creditCard) {
		try {
			
			creditCard.setCreateAt(new Date());
			Mono<ClientModel> client = clientRestService.findById(creditCard.getClientId());
			
			client.subscribe(c ->  {
				log.info( "Cliente: " +  c.getName());
				
				
			});
			
			Mono<CreditCard> save = creditCardDao.createCreditCard(creditCard);
			
			

			log.info("Se ingresó correctamente");
			return ResponseEntity.status(HttpStatus.CREATED).body(save);
			
		} catch (Exception e) {
			log.error("Error" + e);
			return ResponseEntity.internalServerError().body(null);
		}
	}
	
}
