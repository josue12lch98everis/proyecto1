package com.everis.banca.app.credito.microservices.implementations;

import java.util.HashMap;
import java.util.Map;

import com.everis.banca.app.credito.microservices.interfaces.IClientRestService;
import com.everis.banca.app.credito.models.ClientModel;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ClientRestServiceImpl implements IClientRestService {

    @Autowired
	private WebClient webClient;

    @Override
    public Mono<ClientModel> findById(String id) {

        log.info("id: " + id);
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return webClient				
				.get()
				.uri("/{id}", params)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(ClientModel.class)
				;
    }

    @Override
    public Flux<ClientModel> findAll() {
        return webClient
				.get()
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(ClientModel.class);
    }

}