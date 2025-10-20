package com.marketinfo.StockMonitor.infrastructure.RabbitMQ.scheduler;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marketinfo.StockMonitor.infrastructure.finnhub.FinnhubService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StockQuoteScheduler {

    private final FinnhubService finnhubService;
    private final RabbitTemplate rabbitTemplate;

    private final List<String> symbols = Arrays.asList(
            "AAPL", "GOOGL", "MSFT", "AMZN", "NVDA",
            "TSLA", "META", "JPM", "V", "JNJ"
    );
    
    private final AtomicInteger symbolIndex = new AtomicInteger(0);

    public StockQuoteScheduler(FinnhubService finnhubService, RabbitTemplate rabbitTemplate) {
        this.finnhubService = finnhubService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 10000) // Cada 10 segundos
    public void fetchStockQuotes() throws Exception {
        String currentSymbol = symbols.get(symbolIndex.get());

        System.out.println("Fetching quote for: " + currentSymbol);
        finnhubService.getQuote(currentSymbol).ifPresent(quote -> {
            
            System.out.println("SUCCESS: " + quote.toString());

            rabbitTemplate.convertAndSend("stock-quotes", quote);

            System.out.println("✅ Sent quote to RabbitMQ topic 'stock-quotes' for: " + quote.getSymbol());

        });

        symbolIndex.set((symbolIndex.get() + 1) % symbols.size());
    }
}