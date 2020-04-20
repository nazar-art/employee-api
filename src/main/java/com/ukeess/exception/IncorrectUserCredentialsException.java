package com.ukeess.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nazar Lelyak.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect username or password")
public class IncorrectUserCredentialsException extends RuntimeException {
    private static final long serialVersionUID = 0L;

    public IncorrectUserCredentialsException() {
        super();
    }
}
