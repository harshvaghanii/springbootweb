package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeEntity> getAllEmployees() {
        return repository.findAll();
    }

    public EmployeeEntity getEmployeeById(String id) {
        return repository.findById(Long.parseLong(id)).orElse(null);
    }

    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        return repository.save(employee);
    }

    public void deleteEmployee(String id) {
        repository.deleteById(Long.parseLong(id));
    }




}
