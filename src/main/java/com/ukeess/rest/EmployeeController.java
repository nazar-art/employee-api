package com.ukeess.rest;

import com.ukeess.rest.dto.EmployeeDTO;
import com.ukeess.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author Nazar Lelyak.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ApiOperation(
            value = "Create new Employee",
            notes = "Provide an Employee to be added in a body",
            response = EmployeeDTO.class
    )
    public ResponseEntity<EmployeeDTO> createEmployee(
            @ApiParam(value = "Employee object to be created", required = true)
            @RequestBody @Valid EmployeeDTO employeeDTO) {

        EmployeeDTO saved = employeeService.create(employeeDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(saved);
    }

    @GetMapping
    @ApiOperation(value = "Find All Employees", response = EmployeeDTO.class)
    public ResponseEntity<Page<EmployeeDTO>> findAllEmployees(
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(
                employeeService.findAll(pageable)
        );
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Find Employee by ID",
            response = EmployeeDTO.class,
            notes = "Provide an id to look up specific employee"
    )
    public ResponseEntity<EmployeeDTO> findEmployeeById(
            @ApiParam(value = "ID value for the employee you need to retrieve", required = true)
            @PathVariable int id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update an Employee",
            response = EmployeeDTO.class,
            notes = "Provide an id of employee for update, and new representation of employee"
    )
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @ApiParam(value = "ID value for the employee you need to update", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated instance for employee object", required = true)
            @RequestBody @Valid EmployeeDTO newEmployee) {

        return ResponseEntity.ok(employeeService.update(id, newEmployee));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete an Employee",
            notes = "Provide an employee's id"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(
            @ApiParam(value = "ID value for the employee you need to delete", required = true)
            @PathVariable int id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/search")
    @ApiOperation(
            notes = "Provide a name snippet", response = EmployeeDTO.class,
            value = "Search for all Employees which name starts with provided name snippet"
    )
    public ResponseEntity<Page<EmployeeDTO>> searchEmployeesByNameStartsWith(
            @ApiParam(value = "Name snippet for the employee you looking for", required = true)
            @RequestParam(value = "name") String nameSnippet,
            @RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(employeeService.searchByNameStartsWith(nameSnippet,
                PageRequest.of(pageNumber, pageSize)));
    }
}
