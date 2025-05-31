package com.group3.ECommerce.categorize.exception;

import com.group3.ECommerce.categorize.dtos.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for API exceptions
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles CategoryNotFoundException and returns appropriate response
     *
     * @param ex the exception that was thrown
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        ResponseDTO<Void> response = ResponseDTO.error(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles general exceptions
     *
     * @param ex the exception that was thrown
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Void>> handleGeneralException(Exception ex) {
        ResponseDTO<Void> response = ResponseDTO.error("An unexpected error occurred: " + ex.getMessage(), 
                                                      HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}