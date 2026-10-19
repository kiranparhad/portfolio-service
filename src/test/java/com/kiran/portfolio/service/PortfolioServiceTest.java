
package com.kiran.portfolio.service;

//import com.kiran.portfolio.domain.Portfolio;
import com.kiran.portfolio.repository.InMemoryPortfolioRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioServiceTest {

    @Test
    void shouldReturnPortfolioWhenIdExists() {
        // Arrange
        PortfolioService service =
                new PortfolioService(new InMemoryPortfolioRepository());

        // Act
        var result = service.getPortfolioById("P-1001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Equities Portfolio", result.get().name());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        // Arrange
        PortfolioService service =
                new PortfolioService(new InMemoryPortfolioRepository());

        // Act
        var result = service.getPortfolioById("P-9999");

        // Assert
        assertTrue(result.isEmpty());
    }
}
