package com.ukeess.service;

import com.ukeess.model.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeService {

    EmployeeDTO create(EmployeeDTO employeeDTO);

    Page<EmployeeDTO> findAll(Pageable pageable);

    Optional<EmployeeDTO> findById(Integer id);

    EmployeeDTO update(Integer id, EmployeeDTO employeeDTO);

    void deleteById(Integer id);

    Page<EmployeeDTO> searchByNameStartsWithPageable(String nameSnippet, Pageable pageable);
}
