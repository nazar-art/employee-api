package com.ukeess.service.impl;

import com.ukeess.entity.Department;
import com.ukeess.repository.DepartmentRepository;
import com.ukeess.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }
}
