package com.kiran.portfolio.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreatePortfolioRequest(
    @NotBlank String id,
    @NotBlank String name,
    @NotBlank
    @Pattern(regexp = "[A-Z]{3}")
    String baseCurrency
) {}