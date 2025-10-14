package com.marketinfo.StockMonitor.Stock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Assertions;

import com.marketinfo.StockMonitor.Company.entity.Company;
import com.marketinfo.StockMonitor.Company.repository.CompanyRepository;
import com.marketinfo.StockMonitor.Stock.entity.Stock;
import com.marketinfo.StockMonitor.Stock.repository.StockRepository;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.repository.SymbolRepository;

@DataJpaTest
@ActiveProfiles("test")
public class StockIntegrationTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SymbolRepository symbolRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Test
    public void testSaveStock() {
        Company company = new Company();
        Symbol symbol = new Symbol();
        Stock stock = new Stock();

        company.setName("Apple Inc.");
        companyRepository.save(company);
        symbol.setName("AAPL");
        symbol.setLastUpdated(LocalDateTime.now());
        symbol.setCompany(company);
        symbolRepository.save(symbol);
        stock.setPrice(BigDecimal.valueOf(100.0));
        stock.setLastPrice(BigDecimal.valueOf(110.0));
        stock.setPercentChange(BigDecimal.valueOf(10.0));
        stock.setSymbol(symbol);
        stock.setLastUpdated(LocalDateTime.now());

        Stock savedStock = stockRepository.save(stock);

        Assertions.assertNotNull(savedStock);
        Assertions.assertEquals(stock.getPrice(), savedStock.getPrice());
        Assertions.assertEquals("Apple Inc.", savedStock.getSymbol().getCompany().getName());
    }
}
