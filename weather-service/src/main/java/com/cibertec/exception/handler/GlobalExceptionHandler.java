package com.cibertec.exception.handler;

import com.cibertec.dto.internal.ErrorResponse;
import com.cibertec.exception.CityNotFoundException;
import com.cibertec.exception.LocationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchExceptionHandler(
            MethodArgumentTypeMismatchException exception
    ) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        log.warn("Warning: Wrong parameter type! {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterExceptionHandler(
            MissingServletRequestParameterException exception
    ) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        log.warn("Warning: Missing obligatory parameters! {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<?> locationNotFoundExceptionHandler(
            LocationNotFoundException exception
    ) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        log.error("Error: Location not found! {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<?> cityNotFoundExceptionHandler(
            CityNotFoundException exception
    ) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        log.error("Error: City not found! {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
