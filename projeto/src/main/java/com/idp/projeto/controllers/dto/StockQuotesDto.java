package com.idp.projeto.controllers.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.idp.projeto.models.StockQuotes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockQuotesDto {
  private String id;
  private String stockId;
  private Map<String, Long> quotes = new HashMap<String, Long>();

  public StockQuotesDto(StockQuotes stockQuotes) {
    this.id = stockQuotes.getId();
    this.stockId = stockQuotes.getStockId();
    stockQuotes.getQuotes().forEach((quotes) -> this.quotes.put(quotes.getDate(), quotes.getValue()));
  }

  public List<StockQuotesDto> converter(List<StockQuotes> stockQuotes) {
    return stockQuotes.stream().map(StockQuotesDto::new).collect(Collectors.toList());
  }
}
