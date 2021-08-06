package com.everis.banca.app.cuentacorriente.controllers;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.banca.app.cuentacorriente.dao.FixedTermAccountDao;
import com.everis.banca.app.cuentacorriente.models.documents.FixedTermAccount;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/FixedTermAccount")
@Slf4j
public class FixedTermAccountController {



		@Autowired
		private FixedTermAccountDao fixedTermAccountDao;
		
		@GetMapping()
		public Flux<Object> getAllFixedTermAccount() {
			
			Flux<Object> fixedTermAccounts = fixedTermAccountDao.findAll().map(fixedTermAccount ->
				fixedTermAccount
			);
			return fixedTermAccounts;
		}
		
		@PostMapping()
		public Mono<FixedTermAccount> saveFixedTermAccount(@RequestBody FixedTermAccount fixedTermAccount) {
			try {
				fixedTermAccount.setCreateAt(new Date());
				Mono<FixedTermAccount> save = fixedTermAccountDao.save(fixedTermAccount);
				log.info("Se ingresó correctamente");
				return save;
			} catch (Exception e) {
				log.error("Error: " + e);
				return null;
			}
	
			
		}
		@PutMapping()
		public Mono<FixedTermAccount> updateFixedTermAccount(@RequestBody FixedTermAccount fixedTermAccount) {
		
			try {
				fixedTermAccount.setCreateAt(new Date());
				Mono<FixedTermAccount> save = fixedTermAccountDao.save(fixedTermAccount);
				log.info("Se ingresó correctamente");
				return save;
			} catch (Exception e) {
				log.error("Error: " + e);
				return null;	
				}}
		@DeleteMapping()
		public Mono<FixedTermAccount> deleteFixedTermAccount1(@RequestParam(name = "id", required = true) String id) {
			fixedTermAccountDao.findById(id).map
			return null;
		}
	

}
