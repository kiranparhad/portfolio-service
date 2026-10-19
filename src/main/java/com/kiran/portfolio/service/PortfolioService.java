package com.kiran.portfolio.service;

import com.kiran.portfolio.domain.Portfolio;
import com.kiran.portfolio.repository.PortfolioRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service 
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> getPortfolioById(String id) {
        return portfolioRepository.findById(id);
    }

    public Portfolio save(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

}
