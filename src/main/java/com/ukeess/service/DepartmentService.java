package com.ukeess.service;

import com.ukeess.entity.Department;

import java.util.Collection;

public interface DepartmentService {
    Collection<Department> findAllDepartments();
}
