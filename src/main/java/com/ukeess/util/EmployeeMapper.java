package com.ukeess.util;

import com.ukeess.entity.Department;
import com.ukeess.entity.Employee;
import com.ukeess.rest.dto.EmployeeDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class EmployeeMapper {

    public EmployeeDTO mapToDTO(Employee employee) {
        Department empDepartment = employee.getDepartment();
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .active(employee.getActive())
//                .createdAt(toLocalDateTime(employee.getCreatedAt()))
//                .updatedAt(toLocalDateTime(employee.getUpdatedAt()))
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .departmentId(empDepartment.getId())
                .departmentName(empDepartment.getName())
                .build();
    }

    private static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public Employee mapToEmployee(EmployeeDTO dto) {
        Department dep = Department.builder()
                .id(dto.getDepartmentId())
                .name(dto.getDepartmentName())
                .build();
        return Employee.builder()
                .id(dto.getId())
                .name(dto.getName())
                .active(dto.getActive())
                .department(dep)
                .build();
    }
}
