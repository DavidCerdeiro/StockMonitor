package com.marketinfo.StockMonitor.infrastructure.RabbitMQ.service;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.marketinfo.StockMonitor.Stock.dto.StockQuote;

@Service
public class RabbitMQProducerService {

    private static final Logger logger = Logger.getLogger(RabbitMQProducerService.class.getName());
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(StockQuote message) {
        // Enviar el mensaje al tópico "stock-quotes"
        rabbitTemplate.convertAndSend("stock-quotes", message);
        logger.info("Sent message to topic stock-quotes: " + message);
    }
}
