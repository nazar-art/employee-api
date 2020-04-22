package com.ukeess.service.impl;

import com.ukeess.dao.impl.EmployeeDAO;
import com.ukeess.entity.Employee;
import com.ukeess.model.dto.EmployeeDTO;
import com.ukeess.service.EmployeeService;
import com.ukeess.util.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee empl = EmployeeMapper.mapToEmployee(employeeDTO);
        return EmployeeMapper.mapToDTO(employeeDAO.save(empl));
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return EmployeeMapper.mapToDtoList(employeeDAO.getAll());
    }

    @Override
    public Optional<EmployeeDTO> findById(Integer id) {
        return employeeDAO.getById(id)
                .map(EmployeeMapper::mapToDTO);
    }

    @Override
    public EmployeeDTO update(Integer id, EmployeeDTO dto) {
        return EmployeeMapper.mapToDTO(
                employeeDAO.save(EmployeeMapper.mapToEmployee(dto))
        );
    }

    @Override
    public void deleteById(Integer id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> searchByNameStartsWith(String name) {
        return EmployeeMapper.mapToDtoList(employeeDAO.searchByNameStartWith(name));
    }

}
