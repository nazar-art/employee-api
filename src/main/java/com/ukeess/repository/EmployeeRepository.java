package com.ukeess.repository;

import com.ukeess.entity.Employee;
import com.ukeess.rest.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select " +
            "new com.ukeess.rest.dto.EmployeeDTO(e.id, e.name, e.active, e.department.id, e.department.name, e.createdAt, e.updatedAt) " +
            "from Employee e " +
            "where upper(e.name) like concat(upper(?1), '%')")
    Page<EmployeeDTO> findAllByNameIgnoreCaseStartsWith(String name, Pageable pageable);

    @Query("select " +
            "new com.ukeess.rest.dto.EmployeeDTO(e.id, e.name, e.active, e.department.id, e.department.name, e.createdAt, e.updatedAt) " +
            "from Employee e")
    Page<EmployeeDTO> getAllDtos(Pageable pageable);

    @Query("select " +
            "new com.ukeess.rest.dto.EmployeeDTO(e.id, e.name, e.active, e.department.id, e.department.name, e.createdAt, e.updatedAt) " +
            "from Employee e " +
            "where e.id=?1")
    Optional<EmployeeDTO> getEmployeeDtoById(Integer id);
}
