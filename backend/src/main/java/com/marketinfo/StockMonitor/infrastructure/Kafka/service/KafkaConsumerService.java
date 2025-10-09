package com.marketinfo.StockMonitor.infrastructure.Kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.marketinfo.StockQuote.dto.StockQuote;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "stock-quotes", groupId = "stock_group")
    public void consumeMessage(StockQuote message) {
        System.out.println("Received message from topic stock-quotes: " + message);
    }
}
