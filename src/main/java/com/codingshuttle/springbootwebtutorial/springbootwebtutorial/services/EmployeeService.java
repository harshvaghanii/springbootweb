package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees = new ArrayList<>();
        List<EmployeeEntity> list = repository.findAll();
        return list
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(String id) {
        Long empID = Long.parseLong(id);
        EmployeeEntity obj = repository.findById(empID).orElse(null);
        return mapper.map(obj, EmployeeDTO.class);
    }

    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        EmployeeEntity toSaveEntity = mapper.map(employee, EmployeeEntity.class);
        EmployeeEntity savedEntity = repository.save(toSaveEntity);
        return mapper.map(savedEntity, EmployeeDTO.class);
    }

    public void deleteEmployee(String id) {
        repository.deleteById(Long.parseLong(id));
    }




}
