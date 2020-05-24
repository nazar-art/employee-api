package com.ukeess.util;

import com.ukeess.entity.impl.Department;
import com.ukeess.entity.impl.Employee;
import com.ukeess.model.dto.EmployeeDTO;
import lombok.experimental.UtilityClass;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class EmployeeMapper {

    public EmployeeDTO mapToDTO(Employee employee) {
        Department empDp = employee.getDepartment();
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .active(employee.getActive())
                .departmentId(empDp.getId())
                .departmentName(empDp.getName())
                .build();
    }

    public Employee mapToEmployee(EmployeeDTO dto) {
        return Employee.builder()
                .id(dto.getId())
                .name(dto.getName())
                .active(dto.getActive())
                .department(Department.builder()
                        .id(dto.getDepartmentId())
                        .name(dto.getDepartmentName())
                        .build())
                .build();
    }
}
