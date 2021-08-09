package com.everis.banca.app.credito.controllers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.netty.util.concurrent.CompleteFuture;

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
	private ICreditCardService creditService;

	@GetMapping()
	public Mono<ResponseEntity<Flux<CreditCard>>> getAllCreditCard() {

		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(creditService.findAll())
		);
	}

	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<Flux<CreditCard>>> findById(@PathVariable(value = "id") String id) {

		return creditService.findById(id)
				.map(p -> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(creditService.findAll()))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/GetAllCreditCardByClientId/{id}")
	public Flux<CreditCard> getAllCreditCardByIdClient(@PathVariable String id) {
		return creditService.findByClientId(id);
	}
	
	@PostMapping()
	public Mono<ResponseEntity<Map<String, Object>>> createMap(@RequestBody CreditCard creditCard) {

		if (creditCard.getCreateAt() == null) {
			creditCard.setCreateAt(new Date());
		}
		log.info("createAt: " + creditCard.getCreateAt());
		return creditService.createMap(creditCard)
				.flatMap(p -> Mono.just(new ResponseEntity<>(p, HttpStatus.CREATED))
				).onErrorResume(error -> {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("usuario", null);
					params.put("creditcard", null);
					params.put("mensaje", "El cliente no existe");
					params.put("error", error.getMessage());
					return Mono.just(new ResponseEntity<>(params, HttpStatus.NOT_FOUND));
				});
	}
	
}
