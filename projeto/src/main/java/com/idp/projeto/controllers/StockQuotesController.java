package com.idp.projeto.controllers;

import java.util.List;
import java.util.Optional;

import com.idp.projeto.controllers.dto.StockQuotesDto;
import com.idp.projeto.controllers.errors.ResourceException;
import com.idp.projeto.controllers.forms.StockQuotesForm;
import com.idp.projeto.models.StockQuotes;
import com.idp.projeto.repository.StockQuotesRepository;
import com.idp.projeto.services.StockService;

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

@RestController
@RequestMapping({ "/stockQuotes" })
public class StockQuotesController {
  private StockQuotesRepository repository;

  StockQuotesController(StockQuotesRepository stockQuotesRepository) {
    this.repository = stockQuotesRepository;
  }

  @GetMapping()
  @Cacheable("stockQuotes")
  public List<StockQuotesDto> getAllStockQuotes() {
    System.out.println("Get StockQuotes");

    if (repository.findAll().isEmpty()) {
      throw new ResourceException(HttpStatus.NOT_FOUND, "StockQuotes not found.");
    }
    List<StockQuotes> stockQuotes = repository.findAll();

    return new StockQuotesDto().converter(stockQuotes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<StockQuotesDto> getStockQuoteById(@PathVariable(value = "id") String stockId) {
    Optional<StockQuotes> stockQuotesOptional = repository.findByStockId(stockId);

    if (!stockQuotesOptional.isPresent()) {
      throw new ResourceException(HttpStatus.NOT_FOUND, "StockQuote not found.");
    }

    return ResponseEntity.ok(new StockQuotesDto(stockQuotesOptional.get()));

  }

  @PostMapping
  @CacheEvict(value = "stockQuotes", allEntries = true)
  public StockQuotes create(@RequestBody StockQuotesForm stockQuotesForm) {
    StockQuotes stockQuotes = new StockQuotes(stockQuotesForm.getStockId(), stockQuotesForm.convertListDtoToQuotes());

    StockService stockService = new StockService();
    stockService.findStock(stockQuotes.getStockId(), "stockQuotes");

    Optional<StockQuotes> stockQuotesOptional = this.repository.findByStockId(stockQuotes.getStockId());

    if (stockQuotesOptional.isPresent()) {
      StockQuotes gotStockQuotes = stockQuotesOptional.get();

      stockQuotes.getQuotes().forEach(gotStockQuotes::addQuote);

      return repository.save(gotStockQuotes);
    }
    return repository.save(stockQuotes);

  }
}
