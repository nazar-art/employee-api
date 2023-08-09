package com.ukeess.rest;

import com.ukeess.entity.Department;
import com.ukeess.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Nazar Lelyak.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @ApiOperation(
            value = "Find All Departments", response = Department.class, responseContainer = "List"
    )
    public ResponseEntity<List<Department>> findAllDepartments() {
        return ResponseEntity.ok(departmentService.findAllDepartments());
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Find Department by ID",
            notes = "Provide an id to look up specific department",
            response = Department.class
    )
    public ResponseEntity<Department> findDepartmentById(
            @ApiParam(value = "ID value for the department you need to retrieve", required = true)
            @PathVariable int id
    ) {
        return departmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
