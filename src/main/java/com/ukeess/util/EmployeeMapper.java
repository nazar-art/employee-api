package com.ukeess.util;

import com.ukeess.dto.EmployeeDTO;
import com.ukeess.entity.Department;
import com.ukeess.entity.Employee;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nazar Lelyak.
 */
@UtilityClass
public class EmployeeMapper {

    public EmployeeDTO employeeToDTO(Employee employee) {
        Department empDepartment = employee.getDepartment();
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .active(employee.getActive())
                .departmentId(empDepartment.getId())
                .departmentName(empDepartment.getName())
                .build();
    }

    public static Employee dtoToEmployee(EmployeeDTO dto) {
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

    public static List<EmployeeDTO> employeesToDtoList(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeMapper::employeeToDTO)
                .collect(Collectors.toList());
    }
}
