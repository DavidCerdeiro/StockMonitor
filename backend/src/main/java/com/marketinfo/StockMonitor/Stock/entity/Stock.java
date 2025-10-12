package com.marketinfo.StockMonitor.Stock.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Id;

import com.marketinfo.StockMonitor.Symbol.entity.Symbol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Symbol", nullable = false)
    private Symbol symbol;

    @Column(name = "Price", nullable = false)
    private BigDecimal  price;

    @Column(name = "LastPrice", nullable = false)
    private BigDecimal  lastPrice;

    @Column(name = "PercentChange", nullable = false)
    private BigDecimal  percentChange;

    @Column(name = "LastUpdated", nullable = false)
    private LocalDateTime lastUpdated;

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal  price) {
        this.price = price;
    }
    public BigDecimal getLastPrice() {
        return lastPrice;
    }
    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }
    public BigDecimal getPercentChange() {
        return percentChange;
    }
    public void setPercentChange(BigDecimal percentChange) {
        this.percentChange = percentChange;
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
