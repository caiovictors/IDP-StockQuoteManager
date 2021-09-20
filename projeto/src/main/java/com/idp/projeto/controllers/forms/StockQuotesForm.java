package com.idp.projeto.controllers.forms;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.idp.projeto.controllers.dto.QuotesDto;
import com.idp.projeto.models.Quotes;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class StockQuotesForm {
  @NotNull
  @NotEmpty
  private String stockId;

  @NotNull
  @NotEmpty
  private List<QuotesDto> quotes;

  public List<Quotes> convertListDtoToQuotes() {
    List<Quotes> quotes = new ArrayList<Quotes>();
    this.quotes.forEach((quoteDto) -> {
      quotes.add(quoteDto.converter());
    });
    return quotes;
  }
}
