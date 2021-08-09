package com.everis.banca.app.credito;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AppClientConfiguration {
    
    @Value("${config.base.endpoint}")
	private String url;
	
	@Bean
	public WebClient registrarWebClient() {
		return WebClient.create(url);
	}

}
