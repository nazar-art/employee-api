package com.ukeess.repository;

import com.ukeess.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.name LIKE ?1%")
    Page<List<Employee>> searchByNameStartsWith(String name, Pageable pageable);

}
