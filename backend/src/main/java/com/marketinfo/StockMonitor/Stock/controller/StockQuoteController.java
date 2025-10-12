package com.marketinfo.StockMonitor.Stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;
import com.marketinfo.StockMonitor.infrastructure.Kafka.service.KafkaProducerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/stock-quotes")
public class StockQuoteController {
    
    private final KafkaProducerService kafkaProducerService;

    public StockQuoteController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping
    public ResponseEntity<String> sendStockQuote(@RequestBody StockQuote entity) {
        kafkaProducerService.sendMessage(entity);

        return ResponseEntity.ok("Stock quote sent to Kafka topic successfully");
    }
    
}
