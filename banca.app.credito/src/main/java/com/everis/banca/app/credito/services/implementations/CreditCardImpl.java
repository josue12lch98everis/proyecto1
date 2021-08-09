package com.everis.banca.app.credito.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.everis.banca.app.credito.dao.ICreditCardDao;
import com.everis.banca.app.credito.documents.CreditCard;
import com.everis.banca.app.credito.microservices.interfaces.IClientRestService;
import com.everis.banca.app.credito.models.ClientModel;
import com.everis.banca.app.credito.services.interfaces.ICreditCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CreditCardImpl implements ICreditCardService {

	@Autowired
	private ICreditCardDao creditCardDao;

	@Autowired
	private IClientRestService clientRestService;

	@Override
	public Flux<CreditCard> findAll() {
		return creditCardDao.findAll();
	}

	@Override
	public Mono<CreditCard> findById(String id) {
		return creditCardDao.findById(id);
	}

	@Override
	public Mono<CreditCard> create(CreditCard creditCard) {
		return clientRestService.findById(creditCard.getClientId())

				.flatMap(c -> {
					log.info("client: " + c);

					if (c == null) {
						return Mono.just(new CreditCard("No", "", "", "", "", "", null));
					}

					log.info("clientId: " + c.getName());

					if (c.getType().getId() == 1) {
						log.info("no puedes : " + c.getName());
						CreditCard cs = new CreditCard();
						cs.setId("no");
						return Mono.just(cs);
					}
					log.info("paso: " + c);
					return creditCardDao.save(creditCard);

				})

		;
	}

	@Override
	public Mono<Map<String, Object>> createMap(CreditCard creditCard) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		return clientRestService.findById(creditCard.getClientId())
				.flatMap(c -> {
					if (c.getType().getId() == 1) {
						
						try {
							log.info("Validamos cliente");
							creditCardDao.findByClientId(creditCard.getClientId()).collectList().subscribe(x -> {
								log.info("LOGUEANDO RESPUESTA" + x.size());
								params.put("size", x.size());
							});
							Thread.sleep(2000);
							int size = (int) params.get("size");
							if(size > 0) {
								log.info("Mayor 0");
								params.put("usuario", c);
								params.put("creditcard", null);
								params.put("mensaje", "El cliente no puede generar una tarjeta de crédito porque ya cuenta con una existente");
								params.put("error", null);
								return Mono.just(params);
							}
							log.info("Probando");
							creditCardDao.save(creditCard)
									.subscribe(cc -> {
										params.put("creditcard", cc);
									});
							params.put("usuario", c);
							params.put("mensaje", "El cliente puede generar una tarjeta de crédito");
							params.put("error", null);
							
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return Mono.just(params);
					}
					creditCardDao.save(creditCard)
							.subscribe(cc -> {
								params.put("creditcard", cc);
							});
					params.put("usuario", c);
					params.put("mensaje", "El cliente puede generar una tarjeta de crédito");
					params.put("error", null);
					return Mono.just(params);
				});
	}

	@Override
	public Flux<CreditCard> findByClientId(String clientId) {
		Flux<CreditCard> lcreditCard = creditCardDao.findByClientId(clientId);
		return lcreditCard;
	}


}
