package com.ukeess.service;

import com.ukeess.entity.Department;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAllDepartments();

    Optional<Department> findById(Integer id);
}
