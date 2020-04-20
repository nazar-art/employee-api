package com.ukeess.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Nazar Lelyak.
 */
@Data
@Builder
public class EmployeeDTO {
    private Integer id;
    private String name;
    private Boolean active;

    private Integer departmentId;
    private String departmentName;
}
