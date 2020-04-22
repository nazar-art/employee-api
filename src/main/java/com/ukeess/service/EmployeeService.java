package com.ukeess.service;

import com.ukeess.model.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO create(EmployeeDTO employeeDTO);

    //    Page<EmployeeDTO> findAll(Pageable pageable);
    List<EmployeeDTO> findAll();

    Optional<EmployeeDTO> findById(Integer id);

    EmployeeDTO update(Integer id, EmployeeDTO employeeDTO);

    void deleteById(Integer id);

    List<EmployeeDTO> searchByNameStartsWith(String name);
}
