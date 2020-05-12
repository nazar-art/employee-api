package com.ukeess.service.impl;

import com.ukeess.dao.impl.DepartmentDAO;
import com.ukeess.entity.Department;
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

    private final DepartmentDAO departmentDAO;

    @Override
    public List<Department> findAllDepartments() {
        return departmentDAO.getAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentDAO.getById(id);
    }
}
