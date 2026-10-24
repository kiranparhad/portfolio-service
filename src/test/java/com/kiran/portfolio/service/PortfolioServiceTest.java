
package com.kiran.portfolio.service;

import com.kiran.portfolio.domain.Portfolio;
import com.kiran.portfolio.repository.PortfolioRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private PortfolioService portfolioService;


    @Test
    void shouldReturnPortfolioWhenIdExists() {
        // Arrange
        Portfolio portfolio =
                new Portfolio("P-1001", "Equities Portfolio", "USD");

        when(portfolioRepository.findById("P-1001"))
                .thenReturn(Optional.of(portfolio));

        // Act
        var result = portfolioService.getPortfolioById("P-1001");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Equities Portfolio", result.get().name());
        verify(portfolioRepository).findById("P-1001");
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        // Arrange
        when(portfolioRepository.findById("P-9999"))
                .thenReturn(Optional.empty());

        // Act
        var result = portfolioService.getPortfolioById("P-9999");

        // Assert
        assertTrue(result.isEmpty());
        verify(portfolioRepository).findById("P-9999");
    }
}
