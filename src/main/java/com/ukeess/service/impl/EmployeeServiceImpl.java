package com.ukeess.service.impl;

import com.ukeess.entity.Department;
import com.ukeess.entity.Employee;
import com.ukeess.exception.EntityNotFoundException;
import com.ukeess.repository.EmployeeRepository;
import com.ukeess.rest.dto.EmployeeDTO;
import com.ukeess.service.EmployeeService;
import com.ukeess.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
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
        Employee fromDb = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        BeanUtils.copyProperties(dto, fromDb, "id");
        Department newDepartment = Department.builder()
                .id(dto.getDepartmentId())
                .name(dto.getDepartmentName())
                .build();
        if (!Objects.equals(fromDb.getDepartment(), newDepartment)) {
            fromDb.setDepartment(newDepartment);
        }

        return postLoad(employeeRepository.save(fromDb));
    }

    @Override
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeDTO> searchByNameStartsWith(String name, Pageable pageable) {
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
