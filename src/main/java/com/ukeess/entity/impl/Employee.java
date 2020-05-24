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
public class Employee extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Boolean active;
    private Department department;

    @Override
    public String toString() {
        return String.format(
                "Employee{id=%d, name=%s, active=%s, department=%s}",
                this.id, this.name, this.active, this.department
        );
    }
}
