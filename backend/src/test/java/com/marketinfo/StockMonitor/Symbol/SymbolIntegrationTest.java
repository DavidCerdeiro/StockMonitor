package com.marketinfo.StockMonitor.Symbol;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.marketinfo.StockMonitor.Company.entity.Company;
import com.marketinfo.StockMonitor.Company.repository.CompanyRepository;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.repository.SymbolRepository;

@DataJpaTest
@ActiveProfiles("test")
public class SymbolIntegrationTest {

    @Autowired
    private SymbolRepository symbolRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testSaveSymbol() {
        Company company = new Company();
        Symbol symbol = new Symbol();

        company.setName("Apple Inc.");
        companyRepository.save(company);
        symbol.setName("AAPL");
        symbol.setLastUpdated(LocalDateTime.now());
        symbol.setCompany(company);
        Symbol savedSymbol = symbolRepository.save(symbol);

        Assertions.assertNotNull(savedSymbol);
        Assertions.assertEquals(symbol.getName(), savedSymbol.getName());
    }
    
}
