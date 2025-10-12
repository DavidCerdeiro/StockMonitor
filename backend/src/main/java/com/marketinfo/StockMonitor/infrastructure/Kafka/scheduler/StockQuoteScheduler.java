package com.marketinfo.StockMonitor.infrastructure.Kafka.scheduler;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;
import com.marketinfo.StockMonitor.infrastructure.finnhub.FinnhubService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StockQuoteScheduler {

    private final FinnhubService finnhubService;
    private final KafkaTemplate<String, StockQuote> kafkaTemplate;

    private final List<String> symbols = Arrays.asList(
            "AAPL", "GOOGL", "MSFT", "AMZN", "NVDA",
            "TSLA", "META", "JPM", "V", "JNJ"
    );
    
    private final AtomicInteger symbolIndex = new AtomicInteger(0);

    // 2. Inyecta el KafkaTemplate a través del constructor
    public StockQuoteScheduler(FinnhubService finnhubService, KafkaTemplate<String, StockQuote> kafkaTemplate) {
        this.finnhubService = finnhubService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void fetchStockQuotes() throws Exception {
        String currentSymbol = symbols.get(symbolIndex.get());

        System.out.println("Fetching quote for: " + currentSymbol);
        finnhubService.getQuote(currentSymbol).ifPresent(quote -> {
            
            System.out.println("SUCCESS: " + quote.toString());
            
            kafkaTemplate.send("stock-quotes", quote);
            
            System.out.println("✅ Sent quote to Kafka topic 'stock-quotes' for: " + quote.getSymbol());

        });

        symbolIndex.set((symbolIndex.get() + 1) % symbols.size());
    }
}