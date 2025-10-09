package com.marketinfo.StockMonitor.Symbol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketinfo.StockMonitor.Symbol.entity.Symbol;

public interface SymbolRepository extends JpaRepository<Symbol, Integer> {
    
    Optional<Symbol> findByName(String name);
}
