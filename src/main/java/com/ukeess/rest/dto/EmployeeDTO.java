package com.ukeess.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    @Size(min = 2, max = 25)
    private String name;
    @NotNull
    private Boolean active;

    @NotNull
    private Integer departmentId;
    @NotNull
    private String departmentName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
