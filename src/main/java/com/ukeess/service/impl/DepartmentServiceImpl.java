package com.ukeess.service.impl;

import com.ukeess.dao.impl.DepartmentDAO;
import com.ukeess.entity.Department;
import com.ukeess.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDAO;

    @Override
    public Collection<Department> findAllDepartments() {
        return departmentDAO.getAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentDAO.getById(id);
    }
}
