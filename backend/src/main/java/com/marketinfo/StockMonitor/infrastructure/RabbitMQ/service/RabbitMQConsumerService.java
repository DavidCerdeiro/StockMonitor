package com.marketinfo.StockMonitor.infrastructure.RabbitMQ.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;
import com.marketinfo.StockMonitor.Stock.entity.Stock;
import com.marketinfo.StockMonitor.Stock.service.StockService;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.service.SymbolService;

import jakarta.transaction.Transactional;

@Service
public class RabbitMQConsumerService {

    public SymbolService symbolService;
    public StockService stockService;
    // SimpleMessagingTemplate es una clase de Spring que facilita el envío de mensajes a través de WebSockets
    private final SimpMessagingTemplate messagingTemplate;

    public RabbitMQConsumerService(SymbolService symbolService, StockService stockService, SimpMessagingTemplate messagingTemplate) {
        this.symbolService = symbolService;
        this.stockService = stockService;
        this.messagingTemplate = messagingTemplate;
    }
    // Escucha mensajes del tópico "stock-quotes" en RabbitMQ
    // groupId se usa para identificar el grupo de consumidores
    @Transactional
    @RabbitListener(queues = "stock-quotes")
    public void consumeMessage(StockQuote message) {
        Symbol symbol = symbolService.getSymbolByName(message.getSymbol());
        Stock existing = stockService.getStockBySymbol(message.getSymbol());
        Stock stock = existing != null ? existing : new Stock();
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
