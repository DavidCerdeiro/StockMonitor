package com.marketinfo.StockQuote.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockQuote {

    // Mapea los campos JSON de la respuesta de Finnhub a los atributos de esta clase
    @JsonProperty("c")
    private double currentPrice;

    @JsonProperty("dp")
    private double percentChange;

    @JsonProperty("pc")
    private double previousClosePrice;
    
    // Símbolo de la acción (no viene en la respuesta, lo asignamos manualmente)
    private String symbol;

    // Getters y Setters
    public double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }
    public double getPercentChange() { return percentChange; }
    public void setPercentChange(double percentChange) { this.percentChange = percentChange; }
    public double getPreviousClosePrice() { return previousClosePrice; }
    public void setPreviousClosePrice(double previousClosePrice) { this.previousClosePrice = previousClosePrice; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    // toString() para facilitar la impresión en consola
    @Override
    public String toString() {
        return "StockQuote{" +
                "symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                ", percentChange=" + percentChange +
                '}';
    }
}
