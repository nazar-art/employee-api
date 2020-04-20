package com.ukeess.service;

import com.ukeess.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO create(EmployeeDTO employeeDTO);

    Page<EmployeeDTO> findAll(Pageable pageable);

    Optional<EmployeeDTO> findById(Integer id);

    EmployeeDTO update(Integer id, EmployeeDTO employeeDTO);

    void deleteById(Integer id);

    List<EmployeeDTO> searchByNameStartsWith(String name);
}
