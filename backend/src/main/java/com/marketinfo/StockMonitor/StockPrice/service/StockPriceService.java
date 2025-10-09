package com.marketinfo.StockMonitor.StockPrice.service;

import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.StockPrice.entity.StockPrice;
import com.marketinfo.StockMonitor.StockPrice.repository.StockPriceRepository;

@Service
public class StockPriceService {
    public StockPriceRepository stockPriceRepository;

    public StockPriceService(StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }

    public void saveStockPrice(StockPrice stockPrice) {
        stockPriceRepository.save(stockPrice);
    }
}
