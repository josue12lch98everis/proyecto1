package com.everis.banca.app.cuentacorriente.controllers;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.banca.app.cuentacorriente.dao.CurrentAccountDao;
import com.everis.banca.app.cuentacorriente.models.documents.CurrentAccount;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/CurrentAccount")
@Slf4j
public class CurrentAccountController {



		@Autowired
		private CurrentAccountDao currentAccountDao;
		
		@GetMapping()
		public Flux<CurrentAccount> getAllCurrentAccount() {
			
			Flux<CurrentAccount> currentAccounts = currentAccountDao.findAll().map(currentAccount -> {
				return currentAccount;
			});
			return currentAccounts;
		}
		
		@PostMapping()
		public Mono<CurrentAccount> saveCurrentAccount(@RequestBody CurrentAccount currentAccount) {
			try {
				currentAccount.setCreateAt(new Date());
				Mono<CurrentAccount> save = currentAccountDao.save(currentAccount);
				log.info("Se ingresó correctamente");
				return save;
			} catch (Exception e) {
				log.error("Error: " + e);
				return null;
			}
		}
	

}
