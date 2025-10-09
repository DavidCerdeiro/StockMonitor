package com.marketinfo.StockMonitor.infrastructure.Kafka.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marketinfo.StockMonitor.infrastructure.service.FinnhubService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StockQuoteScheduler {

    private final FinnhubService finnhubService;

    // Lista de símbolos de acciones a consultar
    private final List<String> symbols = Arrays.asList(
            "AAPL", "GOOGL", "MSFT", "AMZN", "NVDA",
            "TSLA", "META", "JPM", "V", "JNJ"
    );
    
    // Contador atómico para manejar el índice del símbolo actual de forma segura en concurrencia
    private final AtomicInteger symbolIndex = new AtomicInteger(0);

    public StockQuoteScheduler(FinnhubService finnhubService) {
        this.finnhubService = finnhubService;
    }

    // Cada segundo (1000 ms) consulta la cotización del siguiente símbolo en la lista
    @Scheduled(fixedRate = 1000)
    public void fetchStockQuotes() throws Exception {
        // 1. Obtiene el símbolo actual basado en el índice
        String currentSymbol = symbols.get(symbolIndex.get());

        System.out.println("Fetching quote for: " + currentSymbol);
        finnhubService.getQuote(currentSymbol).ifPresent(quote -> {
            
            // POR AHORA: Imprimimos la cotización en la consola.
            // EN EL FUTURO: Aquí es donde enviarás el objeto 'quote' a Kafka.
            System.out.println("SUCCESS: " + quote.toString());
            
            // ---> futuroKafkaProducer.send("stock-quotes-events", quote);

        });

        // 3. Avanza el índice para la próxima ejecución, volviendo a 0 si llega al final
        symbolIndex.set((symbolIndex.get() + 1) % symbols.size());
    }
}
