package com.ukeess.repository;

import com.ukeess.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    List<Employee> findAllByNameStartingWith(String name);

}
