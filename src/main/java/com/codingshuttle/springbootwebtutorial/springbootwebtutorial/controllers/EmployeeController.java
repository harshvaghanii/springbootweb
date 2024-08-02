package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return repository.findAll();
    }

    @GetMapping("/{employeeID}")
    public EmployeeEntity getEmployeeByID(@PathVariable String employeeID) {
        Long id = Long.parseLong(employeeID);
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee) {
        return repository.save(employee);
    }

    @DeleteMapping("/{employeeID}")
    public void deleteEmployee(@PathVariable String employeeID) {
        Long id = Long.parseLong(employeeID);
        repository.deleteById(id);
    }

}
