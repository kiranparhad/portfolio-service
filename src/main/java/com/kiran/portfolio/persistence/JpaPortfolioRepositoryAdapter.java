package com.kiran.portfolio.persistence;

import org.springframework.stereotype.Repository;

import com.kiran.portfolio.repository.PortfolioRepository;

import com.kiran.portfolio.domain.Portfolio;
import com.kiran.portfolio.exception.DuplicatePortfolioException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaPortfolioRepositoryAdapter implements PortfolioRepository {

    private final PortfolioJpaRepository portfolioJpaRepository;

    public JpaPortfolioRepositoryAdapter(PortfolioJpaRepository portfolioJpaRepository) {
        this.portfolioJpaRepository = portfolioJpaRepository;
    }

    private Portfolio toDomain(PortfolioEntity entity) {
    return new Portfolio(
            entity.getId(),
            entity.getName(),
            entity.getBaseCurrency()
    );
}

    private PortfolioEntity toEntity(Portfolio portfolio) {
        return new PortfolioEntity(
                portfolio.id(),
                portfolio.name(),
                portfolio.baseCurrency()
        );
    }


    @Override
    public List<Portfolio> findAll() {
        return portfolioJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Portfolio> findById(String id) {
        return portfolioJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    @Transactional
    public Portfolio save(Portfolio portfolio) {
        if (portfolioJpaRepository.existsById(portfolio.id())) {
            throw new DuplicatePortfolioException(
                    "Portfolio ID already exists: " + portfolio.id()
            );
        }

        try {
            PortfolioEntity entity = toEntity(portfolio);
            PortfolioEntity savedEntity =
                    portfolioJpaRepository.saveAndFlush(entity);

            return toDomain(savedEntity);

        } catch (DataIntegrityViolationException exception) {
        if (isUniqueConstraintViolation(exception)) {
            throw new DuplicatePortfolioException(
                    "Portfolio ID already exists: " + portfolio.id()
            );
        }

        throw exception;
        }
    }

    private boolean isUniqueConstraintViolation(Throwable exception) {
    Throwable current = exception;

    while (current != null) {
        if (current instanceof SQLException sqlException
                && "23505".equals(sqlException.getSQLState())) {
            return true;
        }

        current = current.getCause();
    }

    return false;
}

}
