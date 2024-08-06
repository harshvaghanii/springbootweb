package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        if (obj == null) return null;
        return mapper.map(obj, EmployeeDTO.class);
    }

    public EmployeeDTO addEmployee(EmployeeDTO employee) {
        EmployeeEntity toSaveEntity = mapper.map(employee, EmployeeEntity.class);
        EmployeeEntity savedEntity = repository.save(toSaveEntity);
        return mapper.map(savedEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployee(String id) {
        Long empID = Long.parseLong(id);
        boolean exists = employeeExistsByID(empID);
        if (!exists) return false;
        repository.deleteById(empID);
        return true;
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employee, Long id) {
        if (!employeeExistsByID(id)) throw new ResourceNotFoundException("Employee Not Found with ID: " + id);
        EmployeeEntity entity = mapper.map(employee, EmployeeEntity.class);
        entity.setId(id);
        EmployeeEntity savedEntity = repository.save(entity);
        return mapper.map(savedEntity, EmployeeDTO.class);
    }

    public boolean employeeExistsByID(Long id) {
        return repository.existsById(id);
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeID, Map<String, Object> updates) {
        boolean exists = employeeExistsByID(employeeID);
        if (!exists) return null;
        EmployeeEntity entity = repository.findById(employeeID).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, entity, value);
        });
        return mapper.map(repository.save(entity), EmployeeDTO.class);
    }


}
