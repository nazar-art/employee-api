package com.ukeess.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull
    @Size(min = 2, max = 25)
    private String name;
    @NotNull
    private Boolean active;

    @NotNull
    private Integer departmentId;
    private String departmentName;
}
