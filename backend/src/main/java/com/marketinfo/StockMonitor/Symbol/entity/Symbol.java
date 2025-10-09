package com.marketinfo.StockMonitor.Symbol.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Id;

import com.marketinfo.StockMonitor.Company.entity.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "symbol")
public class Symbol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name", length = 8, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "Id_Company", nullable = false)
    private Company company;

    @Column(name = "LastUpdated", nullable = false)
    private LocalDateTime lastUpdated;

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
