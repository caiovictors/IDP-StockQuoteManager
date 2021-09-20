package com.idp.projeto.controllers.dto;

import com.idp.projeto.models.Quotes;

import lombok.Data;

@Data
public class QuotesDto {
  private String date;
  private Long value;

  public Quotes converter() {
    Quotes quote = new Quotes();
    quote.setDate(this.getDate());
    quote.setValue(this.getValue());
    return quote;
  }
}
