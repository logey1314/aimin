package com.logey.aimin.ai.config;

import com.logey.aimin.base.response.Result;
import com.logey.aimin.base.response.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<?> businessException(BusinessException ex) {
        String message = ex.getMessage();
        return Result.error(message);
    }
}