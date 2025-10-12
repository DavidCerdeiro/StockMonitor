package com.marketinfo.StockMonitor.Stock.service;

import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.Stock.entity.Stock;
import com.marketinfo.StockMonitor.Stock.repository.StockRepository;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.service.SymbolService;

@Service
public class StockService {
    public StockRepository stockRepository;
    public SymbolService symbolService;

    public StockService(StockRepository stockRepository, SymbolService symbolService) {
        this.stockRepository = stockRepository;
        this.symbolService = symbolService;
    }

    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }

    public Stock getStockBySymbol(String symbol) {
        Symbol symbolEntity = symbolService.getSymbolByName(symbol);
        return stockRepository.findBySymbol(symbolEntity).orElse(null);
    }
}
