package org.example.services;

import org.apache.el.util.ReflectionUtil;
import org.example.dto.EmployeeDTO;
import org.example.entities.EmployeeEntity;
import org.example.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private  final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

//        return new EmployeeDTO(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getEmail() ,employeeEntity.getAge(), employeeEntity.getDateOfJoining(), employeeEntity.isActive());

//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);

//        return modelMapper.map(employeeEntity, EmployeeDTO.class);

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        List<EmployeeDTO> result = new ArrayList<>();
        for(EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
            result.add(employeeDTO);
        }

        return result;


//        return  employeeEntities
//                .stream()
//                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
//                .collect(Collectors.toList());
    }


    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long id) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity updatedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long id, Map<String, Object> updates) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        updates.forEach((key, value) -> {
            Field fieldToUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, key);
            fieldToUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
