package com.idp.projeto.controllers.forms;

import com.idp.projeto.controllers.errors.ResourceException;
import com.idp.projeto.models.Stock;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockForm {
  private String id;
  private String description;

  public Stock convert() {
    if (this.id == null) {
      throw new ResourceException(HttpStatus.BAD_REQUEST, "Error: id field is missing.");
    }
    if (this.description == null) {
      throw new ResourceException(HttpStatus.BAD_REQUEST, "Error: description field is missing.");
    }

    return new Stock(this.id, this.description);
  }
}
