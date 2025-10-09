package com.marketinfo.StockMonitor.infrastructure.Kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.StockPrice.dto.StockQuote;
import com.marketinfo.StockMonitor.StockPrice.entity.StockPrice;
import com.marketinfo.StockMonitor.StockPrice.service.StockPriceService;
import com.marketinfo.StockMonitor.Symbol.entity.Symbol;
import com.marketinfo.StockMonitor.Symbol.service.SymbolService;

@Service
public class KafkaConsumerService {

    public SymbolService symbolService;
    public StockPriceService stockPriceService;
    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerService(SymbolService symbolService, StockPriceService stockPriceService, SimpMessagingTemplate messagingTemplate) {
        this.symbolService = symbolService;
        this.stockPriceService = stockPriceService;
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "stock-quotes", groupId = "stock_group")
    public void consumeMessage(StockQuote message) {
        StockPrice stockPrice = new StockPrice();
        Symbol symbol = symbolService.getSymbolByName(message.getSymbol());
        
        stockPrice.setSymbol(symbol);
        stockPrice.setPrice(message.getCurrentPrice());
        stockPrice.setLastPrice(message.getPreviousClosePrice());
        stockPrice.setPercentChange(message.getPercentChange());
        stockPrice.setDate(message.getLocalDateTime());

        stockPriceService.saveStockPrice(stockPrice);   
        System.out.println("Received message from topic stock-quotes: " + message);

        // Envía el objeto 'message' (StockQuote) al destino "/topic/quotes".
        // Spring lo convertirá automáticamente a JSON.
        // Todos los clientes suscritos a "/topic/quotes" recibirán esta actualización.
        messagingTemplate.convertAndSend("/topic/quotes", message);

        System.out.println("Sent quote update via WebSocket to /topic/quotes for: " + message.getSymbol());
    }
}
