package com.marketinfo.StockMonitor.Company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketinfo.StockMonitor.Company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
}
