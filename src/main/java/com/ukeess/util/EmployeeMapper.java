package com.ukeess.util;

import com.ukeess.rest.dto.EmployeeDTO;
import com.ukeess.entity.Department;
import com.ukeess.entity.Employee;
import lombok.experimental.UtilityClass;

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
                .departmentId(empDepartment.getId())
                .departmentName(empDepartment.getName())
                .build();
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
