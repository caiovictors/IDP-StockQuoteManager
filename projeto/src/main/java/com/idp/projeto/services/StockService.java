package com.idp.projeto.services;

import com.idp.projeto.controllers.errors.ResourceException;
import com.idp.projeto.models.Stock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

  public Stock findStock(String id, String type) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Stock> response = restTemplate.getForEntity("http://localhost:8080/stock/" + id, Stock.class);

    Stock stock = response.getBody();

    if (stock == null) {
      if (type == "stockQuotes") {
        throw new ResourceException(HttpStatus.NOT_FOUND,
            "Stock doesn't exists. You can create a new Stock on '/stock' ");
      }
    } else if (type == "stock") {
      throw new ResourceException(HttpStatus.CONFLICT, "Stock already exists.");
    }

    return stock;
  }
}
