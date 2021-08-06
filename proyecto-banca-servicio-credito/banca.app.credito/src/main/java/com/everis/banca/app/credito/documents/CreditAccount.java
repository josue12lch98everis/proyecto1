package com.everis.banca.app.credito.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document(collection="creditAccount")
@Getter
@Setter
@AllArgsConstructor
public class CreditAccount {
	
	@Id
	private String id;
	
	private String clientId;
	private String accountNumber;
	private Double creditLimit;
	private String creditCardId;
	
	
	
	
}