package com.marketinfo.StockMonitor.Stock;

import org.junit.jupiter.api.Test;

import com.marketinfo.StockMonitor.Stock.entity.Stock;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;

public class StockUnitTest {
    
    @Test
    public void testStockAttributes() {
        Symbol symbol = new Symbol();
        Stock stock = new Stock();
        
        symbol.setName("AAPL");
        stock.setPrice(BigDecimal.valueOf(150.0));
        stock.setLastPrice(BigDecimal.valueOf(300.0));
        stock.setPercentChange(BigDecimal.valueOf(5.5));
        stock.setSymbol(symbol);

        Assertions.assertEquals(BigDecimal.valueOf(150.0), stock.getPrice());
        Assertions.assertEquals(BigDecimal.valueOf(300.0), stock.getLastPrice());
        Assertions.assertEquals(BigDecimal.valueOf(5.5), stock.getPercentChange());
        Assertions.assertEquals("AAPL", stock.getSymbol().getName());
    }
}
