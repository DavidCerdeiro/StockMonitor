import { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import type { StockQuote } from '../schemas/StockQuote';

// Tipo para el mapa de cotizaciones que guardamos en el estado
type QuotesMap = { [key: string]: StockQuote; };

const WEBSOCKET_URL = import.meta.env.VITE_WEBSOCKET_URL || 'ws://localhost:8081/ws/tracker';

export const useWebSocket = (topic: string) => {
    const [quotes, setQuotes] = useState<QuotesMap>({});
    const [isConnected, setIsConnected] = useState(false);

    useEffect(() => {
        const stompClient = new Client({
            brokerURL: WEBSOCKET_URL,
            reconnectDelay: 5000,
            onConnect: () => {
                setIsConnected(true);

                stompClient.subscribe(topic, message => {
                    try {
                        
                        // Recibimos el mensaje y lo parseamos
                        const receivedQuote = JSON.parse(message.body) as StockQuote;

                       
                        if (!receivedQuote || typeof receivedQuote.symbol === 'undefined') {
                            console.error("❌ Parsed message is invalid or missing 'symbol' field:", receivedQuote);
                            return; // No procesar este mensaje
                        }
                        
                        // Actualizar el estado
                        setQuotes(prevQuotes => ({
                            ...prevQuotes,
                            [receivedQuote.symbol]: {
                                ...receivedQuote,
                                receivedAt: new Date(),
                            },
                        }));
                    } catch (error) {
                        console.error("🔴 Failed to parse incoming message:", error);
                    }
                });
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
            },
            onDisconnect: () => {
                setIsConnected(false);
            },
        });

        stompClient.activate();

        return () => {
            if (stompClient.connected) {
                stompClient.deactivate();
            }
        };
    }, [topic]);

    return { quotes: Object.values(quotes), isConnected };
};

