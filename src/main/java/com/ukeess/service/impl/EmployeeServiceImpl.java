package com.ukeess.service.impl;

import com.ukeess.dto.EmployeeDTO;
import com.ukeess.entity.Employee;
import com.ukeess.exception.NotFoundInDbException;
import com.ukeess.repository.DepartmentRepository;
import com.ukeess.repository.EmployeeRepository;
import com.ukeess.service.EmployeeService;
import com.ukeess.util.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee empl = EmployeeMapper.dtoToEmployee(employeeDTO);
        return EmployeeMapper.employeeToDTO(employeeRepository.save(empl));
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(EmployeeMapper::employeeToDTO);
    }

    @Override
    public Optional<EmployeeDTO> findById(Integer id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::employeeToDTO);
    }

    @Override
    public EmployeeDTO update(Integer id, EmployeeDTO dto) {
        if (employeeRepository.existsById(id)
                && departmentRepository.existsById(dto.getDepartmentId())) {

            Employee employee = EmployeeMapper.dtoToEmployee(dto);
            return EmployeeMapper.employeeToDTO(employeeRepository.save(employee));
        }
        throw new NotFoundInDbException(id);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> searchByNameStartsWith(String name) {
        return EmployeeMapper.employeesToDtoList(employeeRepository.findAllByNameStartingWith(name));
    }

}
