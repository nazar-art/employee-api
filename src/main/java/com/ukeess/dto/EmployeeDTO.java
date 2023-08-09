package com.ukeess.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nazar Lelyak.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 15)
    private String name;
    private Boolean active;

    private Integer departmentId;
    private String departmentName;
}
