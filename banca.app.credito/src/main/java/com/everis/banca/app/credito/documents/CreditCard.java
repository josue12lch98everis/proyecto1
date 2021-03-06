package com.everis.banca.app.credito.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection="creditCard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
	
	@Id
	private String id;
	
	private String numberCard;
	private String expirationDate;
	private String cvv;
	private String creditAccountId;
	private String clientId;
	private Date createAt;
	
}
