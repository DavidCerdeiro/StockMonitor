package com.marketinfo.StockMonitor.StockPrice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.marketinfo.StockMonitor.StockPrice.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice, Integer> {
    
}


