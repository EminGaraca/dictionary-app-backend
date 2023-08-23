package com.reeinvent.dictionary.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SynonymsNotFoundException.class)
    public ResponseEntity<ApiError> handleSynonymsNotFoundException() {
        ApiError error = new ApiError(404, "No synonyms found for the given word.", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SameWordAsSynonymException.class)
    public ResponseEntity<ApiError> handleSameWordAsSynonymException() {
        ApiError error = new ApiError(400, "Word cannot be the same as synonym.", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NumberOfCharactersException.class)
    public ResponseEntity<ApiError> handleNuberOfCharactersException() {
        ApiError error = new ApiError(400, "You need to provide more than 2 characters", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSpecialCharactersException.class)
    public ResponseEntity<ApiError> handleNoSpecialCharactersException() {
        ApiError error = new ApiError(400, "Word contains special characters or numbers.", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }
}
