package com.ukeess.entity.impl;

import com.ukeess.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * @author Nazar Lelyak.
 */
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends BaseEntity {

    private String password;
    private Boolean active;
    private String role;

    @Override
    public String toString() {
        return String.format(
                "AuthUser [id=%d, name=%s, active=%s, role=%s]",
                this.id, this.name, this.active, this.role);
    }
}
