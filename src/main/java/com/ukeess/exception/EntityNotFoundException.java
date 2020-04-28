package com.ukeess.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nazar Lelyak.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity is not found in DB")
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String NOT_FOUND_MESSAGE = "Entity, with id/name: %s is NOT FOUND";

    public EntityNotFoundException(String id) {
        super(String.format(NOT_FOUND_MESSAGE, id));
    }
}
