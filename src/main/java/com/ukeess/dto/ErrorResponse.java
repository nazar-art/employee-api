package com.ukeess.dto;

import lombok.Value;

/**
 * @author Nazar Lelyak.
 */
@Value(staticConstructor = "of")
public class ErrorResponse {
    String message;
}
