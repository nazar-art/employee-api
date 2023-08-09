package com.ukeess.rest.dto;

import lombok.Value;

/**
 * @author Nazar Lelyak.
 */
@Value(staticConstructor = "of")
public class ErrorResponse {
    String message;
}
