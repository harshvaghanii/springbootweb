package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
public class EmployeeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to my Spring Boot Application!";
    }

    @GetMapping("/employees/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable String employeeID) {
        return new EmployeeDTO(Long.parseLong(employeeID), "Harsh", "hvaghani@etech.medallia.com", 25, LocalDate.of(2021, 1, 18), false);
    }

    @GetMapping(path = "/employees")
    public String getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return "Hi " + age + " " + sortBy;
    }

}
