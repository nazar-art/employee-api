package com.ukeess.service.impl;

import com.ukeess.dto.EmployeeDTO;
import com.ukeess.entity.Employee;
import com.ukeess.repository.EmployeeRepository;
import com.ukeess.service.EmployeeService;
import com.ukeess.util.EmployeeMapper;
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
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        return postLoad(employeeRepository.save(preSave(dto)));
    }

    @Override
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(this::postLoad);
    }

    @Override
    public Optional<EmployeeDTO> findById(Integer id) {
        return employeeRepository.findById(id)
                .map(this::postLoad);
    }

    @Override
    public EmployeeDTO update(Integer id, EmployeeDTO dto) {
            return postLoad(employeeRepository.save(preSave(dto)));
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeDTO> searchByNameStartsWith(String name, Pageable pageable) {
//        return employeeRepository.findAllByNameStartingWith(name, pageable)
        return employeeRepository.findAllByNameIgnoreCaseStartsWith(name, pageable)
                .map(this::postLoad);
    }


    private Employee preSave(EmployeeDTO dto) {
        return EmployeeMapper.mapToEmployee(dto);
    }

    private EmployeeDTO postLoad(Employee entity) {
        return EmployeeMapper.mapToDTO(entity);
    }
}
