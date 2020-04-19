package com.ukeess.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nazar Lelyak.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity is not found in DB")
public class NotFoundInDbException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String NOT_FOUND_MESSAGE = "Entity, with id: %s is NOT FOUND in DB.";

    public NotFoundInDbException(int id) {
        super(String.format(NOT_FOUND_MESSAGE, id));
    }
}
