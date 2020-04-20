package com.ukeess.service.impl;

import com.ukeess.dto.EmployeeDTO;
import com.ukeess.entity.Employee;
import com.ukeess.exception.EntityNotFoundException;
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

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee empl = EmployeeMapper.mapToEmployee(employeeDTO);
        return EmployeeMapper.mapToDTO(employeeRepository.save(empl));
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(EmployeeMapper::mapToDTO);
    }

    @Override
    public Optional<EmployeeDTO> findById(Integer id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::mapToDTO);
    }

    @Override
    public EmployeeDTO update(Integer id, EmployeeDTO dto) {
        if (employeeRepository.existsById(id)) {
            Employee employee = EmployeeMapper.mapToEmployee(dto);
            return EmployeeMapper.mapToDTO(employeeRepository.save(employee));
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> searchByNameStartsWith(String name) {
        return EmployeeMapper.mapToDtoList(employeeRepository.findAllByNameStartingWith(name));
    }

}
