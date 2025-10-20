package com.marketinfo.StockMonitor.infrastructure.finnhub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.service.SymbolService;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FinnhubService {

    private final RestTemplate restTemplate;
    private final SymbolService symbolService;
    private final Map<String, Symbol> symbolCache = new ConcurrentHashMap<>();

    @Value("${finnhub.api.key}")
    private String apiKey;

    @Value("${finnhub.api.url}")
    private String apiUrl;

    public FinnhubService(RestTemplate restTemplate, SymbolService symbolService) {
        this.restTemplate = restTemplate;
        this.symbolService = symbolService; 
    }

    public Optional<StockQuote> getQuote(String symbol) throws Exception {
        Symbol symbolEntity = symbolCache.computeIfAbsent(symbol, s -> symbolService.getSymbolByName(s));
        if (symbolEntity == null) {
            throw new Exception("Symbol not found in database: " + symbol);
        }

        String url = UriComponentsBuilder.newInstance()
                .uri(java.net.URI.create(apiUrl))
                .queryParam("symbol", symbol)
                .queryParam("token", apiKey)
                .toUriString();

        StockQuote quote = restTemplate.getForObject(url, StockQuote.class);
        if (quote != null) {
            quote.setSymbol(symbol);
            quote.setCompanyName(symbolEntity.getCompany().getName());
            return Optional.of(quote);
        }
        return Optional.empty();
    }
}
