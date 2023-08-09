package com.ukeess.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nazar Lelyak.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;

    @NotEmpty
    @Size(min = 2, max = 25)
    private String name;

    private Boolean active;
    private Department department;
}
