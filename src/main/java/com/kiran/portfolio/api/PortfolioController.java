package com.kiran.portfolio.api;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.kiran.portfolio.domain.Portfolio;
import com.kiran.portfolio.service.PortfolioService;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {
    private final PortfolioService portfolioService;
    private static final Logger log =
        LoggerFactory.getLogger(PortfolioController.class);

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping 
    public List<Portfolio> getPortfolios() {
        log.info("Fetching all portfolios");
        return portfolioService.getAllPortfolios();
    }

    @GetMapping("/{id}")
    public Portfolio getPortfolioById(@PathVariable String id) {
        return portfolioService.getPortfolioById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Portfolio not found: " + id
                )
            );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Portfolio createPortfolio(
        @Valid @RequestBody CreatePortfolioRequest request) {
            Portfolio portfolio = new Portfolio(
                request.id(),
                request.name(),
                request.baseCurrency()
            );

        return portfolioService.save(portfolio);
    }



}
