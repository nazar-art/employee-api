package com.ukeess.repository;

import com.ukeess.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    //    @Query(value = "select e from Employee e where e.name like ?1%")
    List<Employee> findAllByNameStartingWith(String name);

}
