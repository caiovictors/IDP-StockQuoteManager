package com.idp.projeto.controllers;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import com.idp.projeto.controllers.errors.ResourceException;
import com.idp.projeto.controllers.forms.StockForm;
import com.idp.projeto.models.Stock;
import com.idp.projeto.services.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

  @GetMapping()
  @Cacheable("stocks")
  public ResponseEntity<Stock[]> getStocks() {
    System.out.println("Get stocks");

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Stock[]> response = restTemplate.getForEntity("http://localhost:8080/stock", Stock[].class);

    if (response == null) {
      throw new ResourceException(HttpStatus.NOT_FOUND, "Stocks not found.");
    }
    return ResponseEntity.ok(response.getBody());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Stock> getStock(@PathVariable String id) {
    RestTemplate restTemplate = new RestTemplate();
    Stock response = restTemplate.getForObject("http://localhost:8080/stock/{id}", Stock.class, id);

    if (response == null) {
      throw new ResourceException(HttpStatus.NOT_FOUND, "Stock not found.");
    }
    return ResponseEntity.ok(response);
  }

  @PostMapping
  @Transactional
  @CacheEvict(value = "stocks", allEntries = true)
  public ResponseEntity<Stock> createStock(@RequestBody StockForm stockForm) {
    Stock stock = stockForm.convert();

    StockService stockService = new StockService();
    stockService.findStock(stock.getId(), "stock");

    RestTemplate restTemplate = new RestTemplate();
    Stock response = restTemplate.postForObject("http://localhost:8080/stock", stock, Stock.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
