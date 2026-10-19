package com.kiran.portfolio.api;

public record ApiError(
    int status,
    String error,
    String message
){}