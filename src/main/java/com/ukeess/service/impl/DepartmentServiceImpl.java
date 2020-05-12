package com.ukeess.service.impl;

import com.ukeess.entity.Department;
import com.ukeess.repository.DepartmentRepository;
import com.ukeess.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }
}
