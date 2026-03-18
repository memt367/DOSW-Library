package edu.eci.dosw.tdd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, "Not Found", exception.getMessage()));
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<ApiError> handleBookNotAvailable(BookNotAvailableException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(409, "Conflict", exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(400, "Bad Request", exception.getMessage()));
    }
}
