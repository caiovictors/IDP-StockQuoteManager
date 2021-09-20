package com.idp.projeto.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Quotes {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String date;
  private Long value;
}
