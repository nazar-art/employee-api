package com.ukeess.security.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
public class AuthRequestDto implements Serializable {
    private static final long serialVersionUID = 0L;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
