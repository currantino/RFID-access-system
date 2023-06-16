package com.currantino.rfidserver.exception.handler;

import com.currantino.rfidserver.exception.AccessDeniedException;
import com.currantino.rfidserver.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(final ResourceNotFoundException e) {
        return ErrorResponse.builder()
                .error("Не удалось найти запрашиваемый ресурс!")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(final AccessDeniedException e) {
        return ErrorResponse.builder()
                .error("Доступ запрещен.")
                .message(e.getMessage())
                .build();
    }
}
