package com.idp.projeto.repository;

import java.util.Optional;

// import java.util.UUID;

import com.idp.projeto.models.StockQuotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockQuotesRepository extends JpaRepository<StockQuotes, String> {
  Optional<StockQuotes> findByStockId(String stockId);
}