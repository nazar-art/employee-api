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
public class Department {

    private Integer id;

    @NotEmpty
    @Size(min = 1, max = 25)
    private String name;
}
