package com.marketinfo.StockMonitor.Stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;
import com.marketinfo.StockMonitor.infrastructure.RabbitMQ.service.RabbitMQProducerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/stock-quotes")
public class StockQuoteController {
    
    private final RabbitMQProducerService rabbitMQProducerService;

    public StockQuoteController(RabbitMQProducerService rabbitMQProducerService) {
        this.rabbitMQProducerService = rabbitMQProducerService;
    }

    @PostMapping
    public ResponseEntity<String> sendStockQuote(@RequestBody StockQuote entity) {
        rabbitMQProducerService.sendMessage(entity);

        return ResponseEntity.ok("Stock quote sent to RabbitMQ topic successfully");
    }
    
}
