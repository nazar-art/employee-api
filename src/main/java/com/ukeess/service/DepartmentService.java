package com.ukeess.service;

import com.ukeess.entity.impl.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAllDepartments();

    Optional<Department> findById(Integer id);
}
