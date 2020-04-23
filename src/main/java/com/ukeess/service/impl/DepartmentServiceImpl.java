package com.ukeess.service.impl;

import com.ukeess.dao.impl.DepartmentDAO;
import com.ukeess.entity.Department;
import com.ukeess.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDAO;

    @Override
    public Page<Department> findAllDepartments(Pageable pageable) {
        return departmentDAO.getAll(pageable);
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentDAO.getById(id);
    }
}
