package com.cibertec.exception.handler;

import com.cibertec.dto.internal.ErrorResponse;
import com.cibertec.exception.LocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchExceptionHandler(
            MethodArgumentTypeMismatchException exception
    ) {
        ErrorResponse errorInternalResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInternalResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterExceptionHandler(
            MissingServletRequestParameterException exception
    ) {
        ErrorResponse errorInternalResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInternalResponse);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<?> locationNotFoundExceptionHandler(
            LocationNotFoundException exception
    ) {
        ErrorResponse errorInternalResponse = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInternalResponse);
    }
}
