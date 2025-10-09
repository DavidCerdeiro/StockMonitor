package com.marketinfo.StockMonitor.Symbol.service;

import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.repository.SymbolRepository;

@Service
public class SymbolService {
    public SymbolRepository symbolRepository;
    
    public SymbolService(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    public Symbol getSymbolByName(String name) {
        return symbolRepository.findByName(name).orElse(null);
    }
}
