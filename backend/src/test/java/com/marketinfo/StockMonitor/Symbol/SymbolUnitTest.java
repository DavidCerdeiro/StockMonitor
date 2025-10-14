package com.marketinfo.StockMonitor.Symbol;

import org.junit.jupiter.api.Test;

import com.marketinfo.StockMonitor.Company.entity.Company;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;

public class SymbolUnitTest {
    
    @Test
    public void testSymbolAttributes() {
        Company company = new Company();
        Symbol symbol = new Symbol();
        
        company.setName("Apple Inc.");
        symbol.setName("AAPL");
        symbol.setLastUpdated(LocalDateTime.now());
        symbol.setCompany(company);
        
        Assertions.assertEquals("AAPL", symbol.getName());
        Assertions.assertEquals("Apple Inc.", symbol.getCompany().getName());
        Assertions.assertEquals(LocalDateTime.now().getMinute(), symbol.getLastUpdated().getMinute());
    }
}
