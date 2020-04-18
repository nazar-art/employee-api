package com.ukeess.service.impl;

import com.ukeess.entity.Department;
import com.ukeess.repository.DepartmentRepository;
import com.ukeess.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public Collection<Department> findAllDepartments() {
        return (Collection<Department>) departmentRepository.findAll();
    }
}
