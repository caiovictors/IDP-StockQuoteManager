package com.projetoIdp.projeto.models;

import java.util.List;

import com.idp.projeto.controllers.StockController;
import com.idp.projeto.controllers.errors.ExceptionHandlerAdvice;
import com.idp.projeto.controllers.errors.ResourceException;
import com.idp.projeto.controllers.forms.StockForm;
import com.idp.projeto.models.Stock;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StockControllerTests {
  @Mock
  private StockController stockController;

  @Mock
  private Stock stock;

  @Mock
  private StockForm stockForm;

  @BeforeEach
  public void BeforeEach() {
    MockitoAnnotations.initMocks(this);
    this.stockController = new StockController();
    this.stock = new Stock();
    this.stockForm = new StockForm();
  }

  @Test
  public void shouldGetStockById() {

    ResponseEntity<Stock> response = stockController.getStock("petr4");
    Stock stock = response.getBody();
    Assert.assertEquals(stock.getId(), "petr4");
    Assert.assertEquals(stock.getDescription(), "Petrobras PN");
  }

  // @Test
  // public void shouldNotGetStock() {

  // ResponseEntity<Stock> response = stockController.getStock("petraa");
  // Stock stock = response.getBody();

  // Assert.assertThrows(ResourceException.class, "Stock not found.");
  // }

  // @Test
  // void shouldAddStockSuccessfullyTest() {
  // StockForm stockForm = new StockForm("mglu3", "Magazine Luiza");
  // Stock stock = stockController.createStock(stockForm).getBody();

  // Assert.assertTrue(stock.getId().equals("mglu3"));
  // Assert.assertEquals(stock.getDescription(), "Magazine Luiza");
  // }
}
