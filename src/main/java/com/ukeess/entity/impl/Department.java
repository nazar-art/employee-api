package com.ukeess.entity.impl;

import com.ukeess.entity.BaseEntity;
import lombok.experimental.SuperBuilder;


/**
 * @author Nazar Lelyak.
 */
@SuperBuilder
public class Department extends BaseEntity {
    @Override
    public String toString() {
        return String.format("Department [id=%d, name=%s]", this.id, this.name);
    }
}
