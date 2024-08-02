package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return "Hi " + age + " " + sortBy;
    }

    @GetMapping("/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable String employeeID) {
        return new EmployeeDTO(Long.parseLong(employeeID), "Harsh", "hvaghani@etech.medallia.com", 25, LocalDate.of(2021, 1, 18), false);
    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee) {
        employee.setId(123L);
        return employee;
    }

}
