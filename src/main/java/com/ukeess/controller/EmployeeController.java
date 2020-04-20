package com.ukeess.controller;

import com.ukeess.dto.EmployeeDTO;
import com.ukeess.service.EmployeeService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.create(employeeDTO));
    }

    @GetMapping
    public ResponseEntity findAllEmployees(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                                           @RequestParam(value = "size", defaultValue = "1") int pageSize) {
        return ResponseEntity.ok(
                employeeService.findAll(PageRequest.of(pageNumber, pageSize))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable int id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@PathVariable int id, @RequestBody @Valid EmployeeDTO newEmployee) {
        return ResponseEntity.ok(employeeService.update(id, newEmployee));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
    }

    @GetMapping("/search")
    public List<EmployeeDTO> searchEmployeesByNameStartsWith(@RequestParam(value = "name") String nameSnippet) {
        return employeeService.searchByNameStartsWith(nameSnippet);
    }
}
