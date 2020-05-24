package com.ukeess.service.impl;

import com.ukeess.dao.impl.EmployeeDAO;
import com.ukeess.entity.impl.Employee;
import com.ukeess.model.dto.EmployeeDTO;
import com.ukeess.service.EmployeeService;
import com.ukeess.util.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        return postLoad(employeeDAO.save(preSave(dto)));
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        return employeeDAO.getAll(pageable)
                .map(this::postLoad);
    }

    @Override
    public Optional<EmployeeDTO> findById(Integer id) {
        return employeeDAO.getById(id)
                .map(this::postLoad);
    }

    @Override
    public EmployeeDTO update(Integer id, EmployeeDTO dto) {
        return postLoad(employeeDAO.save(preSave(dto)));
    }

    @Override
    public void deleteById(Integer id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public Page<EmployeeDTO> searchByNameStartsWith(String nameSnippet, Pageable pageable) {
        Assert.hasText(nameSnippet, "name of the employee can't be empty");
        return employeeDAO.findAllEmployeesWithNameStartsWith(nameSnippet, pageable)
                .map(this::postLoad);
    }


    private Employee preSave(EmployeeDTO dto) {
        return EmployeeMapper.mapToEmployee(dto);
    }

    private EmployeeDTO postLoad(Employee entity) {
        return EmployeeMapper.mapToDTO(entity);
    }
}
