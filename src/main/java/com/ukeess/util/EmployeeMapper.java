package com.ukeess.util;

import com.ukeess.entity.Department;
import com.ukeess.entity.Employee;
import com.ukeess.model.dto.EmployeeDTO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nazar Lelyak.
 */
@Slf4j
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

    public List<EmployeeDTO> mapToDtoList(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
