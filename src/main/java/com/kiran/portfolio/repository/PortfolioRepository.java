package com.kiran.portfolio.repository;

import com.kiran.portfolio.domain.Portfolio;
import java.util.List;
import java.util.Optional;

public interface PortfolioRepository {

    List<Portfolio> findAll();

    Optional<Portfolio> findById(String id);

    Portfolio save(Portfolio portfolio);

}
