package com.ukeess.controller;

import com.ukeess.entity.Employee;
import com.ukeess.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Nazar Lelyak.
 */
//@CrossOrigin(origins = "http://localhost:4200",
//        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@AllArgsConstructor
@RequestMapping("/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    @PostMapping
    @ApiOperation(value = "Create new Employee",
            notes = "Provide an Employee to be added in a body",
            response = Employee.class)
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.create(employee));
    }

    @GetMapping
    @ApiOperation(value = "Find All Employees", response = Employee.class)
    public ResponseEntity<Page<Employee>> findAllEmployees(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "1") int pageSize) {
        return ResponseEntity.ok(
                employeeService.findAll(PageRequest.of(pageNumber, pageSize))
        );
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find Employee by ID", response = Employee.class,
            notes = "Provide an id to look up specific employee")
    public ResponseEntity<Employee> findEmployeeById(
            @ApiParam(value = "ID value for the employee you need to retrieve", required = true)
            @PathVariable int id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an Employee", response = Employee.class,
            notes = "Provide an id of employee for update, and new representation of employee")
    public ResponseEntity<Employee> updateEmployee(
            @ApiParam(value = "ID value for the employee you need to update", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated instance for employee object", required = true)
            @RequestBody @Valid Employee newEmployee) {

        return ResponseEntity.ok(employeeService.update(id, newEmployee));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an Employee",
            notes = "Provide an employee's id")
    public void deleteEmployee(
            @ApiParam(value = "ID value for the employee you need to delete", required = true)
            @PathVariable int id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/search")
    @ApiOperation(notes = "Provide a name snippet", response = Employee.class, responseContainer = "List",
            value = "Search for all Employees which name starts with provided name snippet")
    public List<Employee> searchEmployeesByNameStartsWith(
            @ApiParam(value = "Name snippet for the employee you looking for", required = true)
            @RequestParam(value = "name") String nameSnippet) {

        return employeeService.searchByNameStartsWith(nameSnippet);
    }
}
