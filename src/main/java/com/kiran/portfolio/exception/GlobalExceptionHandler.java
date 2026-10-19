package com.kiran.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.kiran.portfolio.api.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatePortfolioException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDuplicatePortfolio(
            DuplicatePortfolioException exception) {
            return new ApiError(HttpStatus.CONFLICT.value(),
                                "Conflict",
                                exception.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInputValidation(
            MethodArgumentNotValidException exception) {
            return new ApiError(HttpStatus.BAD_REQUEST.value(),
                                "Bad Request",
                                exception.getBindingResult()
                                        .getAllErrors()
                                        .get(0)
                                        .getDefaultMessage());
            }
}