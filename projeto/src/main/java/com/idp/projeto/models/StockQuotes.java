package com.idp.projeto.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StockQuotes {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private String stockId;

  @OneToMany(cascade = javax.persistence.CascadeType.ALL)
  private List<Quotes> quotes = new ArrayList<>();

  public StockQuotes(String stockId, List<Quotes> quotes) {
    this.stockId = stockId;
    this.quotes = quotes;
  }

  public void addQuote(Quotes quote) {
    this.quotes.add(quote);
  }
}
