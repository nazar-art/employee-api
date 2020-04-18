package com.ukeess.controller;

import com.ukeess.entity.Department;
import com.ukeess.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Nazar Lelyak.
 */
//@CrossOrigin(origins = "http://localhost:4200",
//        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @GetMapping
    public Collection<Department> findAll() {
        return departmentService.findAllDepartments();
    }
}
