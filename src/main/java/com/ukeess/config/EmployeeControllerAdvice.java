package com.ukeess.config;

import com.ukeess.dto.ErrorResponse;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * @author Nazar Lelyak.
 */
@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFoundEmployee(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Invalid ID provided. This ID doesn't exists at DB"));
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleNotFoundEmployee(MalformedJwtException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("You should authorise first"));
    }

}
