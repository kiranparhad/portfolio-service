package com.kiran.portfolio.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE
)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio_db",
        "spring.datasource.username=kiran",
        "spring.datasource.password=${DB_PASSWORD}",
        "spring.jpa.hibernate.ddl-auto=validate"
})
class PortfolioJpaRepositoryTest {

    @Autowired
    private PortfolioJpaRepository portfolioJpaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldSaveAndRetrievePortfolio() {
        // Arrange
        PortfolioEntity portfolio = new PortfolioEntity(
                "P-TEST-001",
                "Integration Test Portfolio",
                "USD"
        );

        // Act
        portfolioJpaRepository.saveAndFlush(portfolio);

        var result = portfolioJpaRepository.findById("P-TEST-001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Integration Test Portfolio", result.get().getName());
        assertEquals("USD", result.get().getBaseCurrency());
    }

    @Test
    void shouldRejectDuplicatePortfolioId() {
        jdbcTemplate.update(
                "INSERT INTO portfolios (id, name, base_currency) VALUES (?, ?, ?)",
                "P-DUP-001", "First Portfolio", "USD"
        );

        assertThrows(
                DuplicateKeyException.class,
                () -> jdbcTemplate.update(
                        "INSERT INTO portfolios (id, name, base_currency) VALUES (?, ?, ?)",
                        "P-DUP-001", "Duplicate Portfolio", "EUR"
                )
        );
    }



}