package com.kiran.portfolio.repository;

import java.util.List;
import java.util.Optional;

import java.util.Map;
import java.util.HashMap;

import com.kiran.portfolio.domain.Portfolio;
import com.kiran.portfolio.exception.DuplicatePortfolioException;

import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Profile;

@Profile("memory")
@Repository
public class InMemoryPortfolioRepository implements PortfolioRepository{
    private final Map<String, Portfolio> portfolios = new HashMap<>();

    public InMemoryPortfolioRepository() {
        portfolios.put("P-1001",
            new Portfolio("P-1001", "Equities Portfolio", "USD"));

        portfolios.put("P-1002",
            new Portfolio("P-1002", "Fixed Income Portfolio", "EUR"));
    }

    public List<Portfolio> findAll() {
        return List.copyOf(portfolios.values());
    }

    public Optional<Portfolio> findById(String id) {
        return Optional.ofNullable(portfolios.get(id));
    }

    public Portfolio save(Portfolio portfolio){
        if (portfolios.containsKey(portfolio.id())){
            throw new DuplicatePortfolioException(
                "Portfolio ID already exists: " + portfolio.id()
            );
        }
        portfolios.put(portfolio.id(), portfolio);
        return portfolio;


    }
}
