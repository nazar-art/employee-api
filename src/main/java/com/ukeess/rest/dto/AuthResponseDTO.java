package com.ukeess.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
@AllArgsConstructor
public class AuthResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String token;
}
