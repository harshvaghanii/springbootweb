package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService empService;

    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(empService.getAllEmployees());
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable String employeeID) {
        EmployeeDTO employee = empService.getEmployeeById(employeeID);
        if (employee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employee) {
        EmployeeDTO savedEmployee = empService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employee, @PathVariable Long employeeID) {
        EmployeeDTO editedEmployee = empService.updateEmployeeById(employee, employeeID);
        return ResponseEntity.ok(editedEmployee);
    }

    @DeleteMapping("/{employeeID}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable String employeeID) {
        boolean deleted = empService.deleteEmployee(employeeID);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeID, @RequestBody Map<String, Object> updates) {
        EmployeeDTO employee = empService.updatePartialEmployeeById(employeeID, updates);
        return employee == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(employee);
    }

}
