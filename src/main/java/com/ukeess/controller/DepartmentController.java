package com.ukeess.controller;

import com.ukeess.entity.Department;
import com.ukeess.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Nazar Lelyak.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    @ApiOperation(value = "Find All Departments", responseContainer = "List", response = Department.class)
    public ResponseEntity<Page<Department>> findAllDepartments(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(
                departmentService.findAllDepartments(PageRequest.of(pageNumber, pageSize))
        );
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find Department by ID",
            notes = "Provide an id to look up specific department",
            response = Department.class)
    public ResponseEntity<Department> findDepartmentById(
            @ApiParam(value = "ID value for the department you need to retrieve", required = true)
            @PathVariable int id) {

        return departmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
