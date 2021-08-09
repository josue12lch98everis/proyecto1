package com.everis.banca.app.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.banca.app.cliente.dao.IClientDao;
import com.everis.banca.app.cliente.models.documents.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientDao clientDao;

	@Override
	public Flux<Client> findAll() {

		return clientDao.findAll();
	}

	@Override
	public Mono<Client> findById(String id) {

		return clientDao.findById(id);
	}

	@Override
	public Mono<Client> create(Client client) {

		return clientDao.save(client);
	}

	@Override
	public Mono<Client> update(String id, Client client) {

		return findById(id).flatMap(result -> {

			Client cl = new Client();
			cl.setId(id);
			cl.setCreateAt(result.getCreateAt());
			cl.setName(client.getName());
			cl.setType(client.getType());
			return clientDao.save(cl);
		});
	}

	@Override
	public Mono<Void> delete(Client client) {

		return clientDao.delete(client);
	}

}
