package com.idp.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class StockQuoteManager {
	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManager.class, args);
	}
}
