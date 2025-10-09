package com.marketinfo.StockMonitor.infrastructure.finnhub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.marketinfo.StockMonitor.StockPrice.dto.StockQuote;

import java.util.Optional;

@Service
public class FinnhubService {

    private final RestTemplate restTemplate;

    @Value("${finnhub.api.key}")
    private String apiKey;

    @Value("${finnhub.api.url}")
    private String apiUrl;
    
    public FinnhubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<StockQuote> getQuote(String symbol) throws Exception {
        String url = UriComponentsBuilder.newInstance()
                .uri(java.net.URI.create(apiUrl))
                .queryParam("symbol", symbol)
                .queryParam("token", apiKey)
                .toUriString();
        
        StockQuote quote = restTemplate.getForObject(url, StockQuote.class);
            if (quote != null) {
                quote.setSymbol(symbol);
                return Optional.of(quote);
            }
        
        return Optional.empty();
    }
}
