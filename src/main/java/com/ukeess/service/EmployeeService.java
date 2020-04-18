package com.ukeess.service;

import com.ukeess.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    Page<Employee> findAll(Pageable pageable);

    Optional<Employee> findById(Integer id);

    Employee update(Integer id, Employee employee);

    void deleteById(Integer id);

    Page<List<Employee>> searchByNameStartsWith(String name, Pageable pageable);
}
