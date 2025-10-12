package com.marketinfo.StockMonitor.infrastructure.Kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;
import com.marketinfo.StockMonitor.Stock.entity.Stock;
import com.marketinfo.StockMonitor.Stock.service.StockService;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.service.SymbolService;

@Service
public class KafkaConsumerService {

    public SymbolService symbolService;
    public StockService stockService;
    // SimpleMessagingTemplate es una clase de Spring que facilita el envío de mensajes a través de WebSockets
    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerService(SymbolService symbolService, StockService stockService, SimpMessagingTemplate messagingTemplate) {
        this.symbolService = symbolService;
        this.stockService = stockService;
        this.messagingTemplate = messagingTemplate;
    }
    // Escucha mensajes del tópico "stock-quotes" en Kafka
    // groupId se usa para identificar el grupo de consumidores
    @KafkaListener(topics = "stock-quotes", groupId = "stock_group")
    public void consumeMessage(StockQuote message) {
        Symbol symbol = symbolService.getSymbolByName(message.getSymbol());
        Stock stock = stockService.getStockBySymbol(message.getSymbol()) != null ? stockService.getStockBySymbol(message.getSymbol()) : new Stock();
        stock.setSymbol(symbol);
        stock.setPrice(message.getCurrentPrice());
        stock.setLastPrice(message.getPreviousClosePrice());
        stock.setPercentChange(message.getPercentChange());
        stock.setLastUpdated(message.getLocalDateTime());

        stockService.saveStock(stock);
        System.out.println("Received message from topic stock-quotes: " + message);

        // Envía el objeto 'message' (StockQuote) al destino "/topic/quotes"
        messagingTemplate.convertAndSend("/topic/quotes", message);

        System.out.println("Sent quote update via WebSocket to /topic/quotes for: " + message.getSymbol());
    }
}
