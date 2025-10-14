package com.marketinfo.StockMonitor.infrastructure.RabbitMQ.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "stock-quotes";

    @Bean
    public Queue stockQuotesQueue() {
        // True indica que la cola es durable (persiste después de reinicios del servidor)
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

