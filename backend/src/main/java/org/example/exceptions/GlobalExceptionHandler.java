package org.example.exceptions;

// This class will keep using the message exception but I will
// do the conversion to JSON in one place @RestControllerAdvice

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateUsername(DuplicateUsernameException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body((Map.of("error", ex.getMessage())));
    }

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<Map<String, String>> handleDao(DaoException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body((Map.of("error", ex.getMessage())));
    }

}
