package com.marketinfo.StockMonitor.Stock.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockQuote {
    @JsonProperty("c")
    private double currentPrice;

    @JsonProperty("dp")
    private double percentChange;

    @JsonProperty("pc")
    private double previousClosePrice;

    @JsonProperty("t")
    private long timestamp;
    
    private String symbol;

    private String companyName;

    // Getters y Setters
    public BigDecimal getCurrentPrice() { return BigDecimal.valueOf(currentPrice); }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }
    public BigDecimal getPercentChange() { return BigDecimal.valueOf(percentChange); }
    public void setPercentChange(double percentChange) { this.percentChange = percentChange; }
    public BigDecimal getPreviousClosePrice() { return BigDecimal.valueOf(previousClosePrice); }
    public void setPreviousClosePrice(double previousClosePrice) { this.previousClosePrice = previousClosePrice; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public LocalDateTime getLocalDateTime() { return LocalDateTime.ofEpochSecond(timestamp, 0, java.time.ZoneOffset.UTC); }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getCompanyName() { return companyName; }

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
