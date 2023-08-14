package com.ukeess.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
public class AuthRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;

    @NotNull
    @Size(min = 2, max = 20)
    private String username;

    @NotNull
    @Size(min = 4, max = 15)
    private String password;
}
