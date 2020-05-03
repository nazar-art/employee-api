package com.ukeess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author Nazar Lelyak.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

    private Integer id;
    private String userName;
    @ToString.Exclude
    private String password;
    private Boolean active;
    private String role;
}
