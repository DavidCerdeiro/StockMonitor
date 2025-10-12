package com.marketinfo.StockMonitor.infrastructure.Kafka.service;

import java.util.logging.Logger;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;

@Service
public class KafkaProducerService {

    private static final Logger logger = Logger.getLogger(KafkaProducerService.class.getName());
    // El primer parámetro es el tipo de la clave (String) y el segundo es el tipo del valor (StockQuote)
    private final KafkaTemplate<String, StockQuote> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, StockQuote> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(StockQuote message) {
        // Enviar el mensaje al tópico "stock-quotes"
        kafkaTemplate.send("stock-quotes", message);
        logger.info("Sent message to topic stock-quotes: " + message);
    }
}
