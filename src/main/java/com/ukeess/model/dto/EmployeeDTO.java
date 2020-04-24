package com.ukeess.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String name;
    private Boolean active;

    private Integer departmentId;
    private String departmentName;
}
