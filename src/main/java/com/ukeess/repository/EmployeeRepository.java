package com.ukeess.repository;

import com.ukeess.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Page<Employee> findAllByNameIgnoreCaseStartsWith(String name, Pageable pageable);

}
