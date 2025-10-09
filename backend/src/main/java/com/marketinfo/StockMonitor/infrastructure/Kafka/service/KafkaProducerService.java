package com.marketinfo.StockMonitor.infrastructure.Kafka.service;

import java.util.logging.Logger;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.marketinfo.StockQuote.dto.StockQuote;

@Service
public class KafkaProducerService {

    private static final Logger logger = Logger.getLogger(KafkaProducerService.class.getName());
    private final KafkaTemplate<String, StockQuote> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, StockQuote> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(StockQuote message) {
        kafkaTemplate.send("stock-quotes", message);
        logger.info("Sent message to topic stock-quotes: " + message);
    }
}
