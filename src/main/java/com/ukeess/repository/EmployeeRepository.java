package com.ukeess.repository;

import com.ukeess.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    //@Query(value = "select e from Employee e where e.name like ?1%")
    Page<Employee> findAllByNameStartingWith(String name, Pageable pageable);

}
