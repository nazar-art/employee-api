package com.ukeess.service;

import com.ukeess.entity.Department;

import java.util.Collection;
import java.util.Optional;

public interface DepartmentService {
    Collection<Department> findAllDepartments();

    Optional<Department> findById(Integer id);
}
