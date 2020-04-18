package com.ukeess.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nazar Lelyak.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity is not found")
public class NotPresentedInDbException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String STATION_NOT_FOUND_EXCEPTION = "Entity, with id: %s is NOT FOUND in DB.";

    public NotPresentedInDbException(int id) {
        super(String.format(STATION_NOT_FOUND_EXCEPTION, id));
    }
}
