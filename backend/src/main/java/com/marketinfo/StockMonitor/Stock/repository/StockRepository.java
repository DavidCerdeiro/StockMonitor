package com.marketinfo.StockMonitor.Stock.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Stock.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findBySymbol(Symbol symbol);
}


