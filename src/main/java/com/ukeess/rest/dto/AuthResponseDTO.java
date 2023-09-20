package com.ukeess.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
@AllArgsConstructor(staticName = "of")
public class AuthResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String token;
}
