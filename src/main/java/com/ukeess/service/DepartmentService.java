package com.ukeess.service;

import com.ukeess.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DepartmentService {
    Page<Department> findAllDepartments(Pageable pageable);

    Optional<Department> findById(Integer id);
}
