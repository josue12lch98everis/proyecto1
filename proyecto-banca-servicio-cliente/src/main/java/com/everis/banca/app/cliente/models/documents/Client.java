package com.everis.banca.app.cliente.models.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Document(collection="client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	@Id
	private String id;
	
	private String name;
	private Type type;
	private Date createAt;
}
