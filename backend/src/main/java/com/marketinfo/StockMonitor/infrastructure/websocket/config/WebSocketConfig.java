package com.marketinfo.StockMonitor.infrastructure.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Habilita el servidor de WebSocket y el broker de mensajes
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(@SuppressWarnings("null") MessageBrokerRegistry config) {
        // Habilita un broker de mensajes en memoria simple.
        // Los mensajes cuyo destino empiece con "/topic" serán enrutados al broker.
        // El frontend se suscribirá a destinos como "/topic/quotes".
        config.enableSimpleBroker("/topic");

        // Define el prefijo para los destinos de la aplicación.
        // Los mensajes enviados desde el cliente al servidor (que en este proyecto no usaremos mucho)
        // deberán tener un destino que empiece con "/app".
        config.setApplicationDestinationPrefixes("/app");
    }

    @SuppressWarnings("null")
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registra el endpoint "/ws/tracker".
        // Este es el punto de entrada para la conexión WebSocket.
        // El cliente React se conectará a "http://localhost:8080/ws/tracker".
        registry.addEndpoint("/ws/tracker")
                // Permite conexiones desde cualquier origen (esencial para el desarrollo local
                // donde React corre en un puerto diferente, ej. 3000).
                .setAllowedOrigins("*");
    }
}