package org.example.controller;

import org.example.dto.EmployeeDTO;
import org.example.entities.EmployeeEntity;
import org.example.repositories.EmployeeRepository;
import org.example.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

   private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
       Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
       return employeeDTO
               .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
               .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees(@RequestParam(required = false, name = "myAge") Integer age,
                                             @RequestParam(required = false) String sortBy) {
        return employeeService.getEmployees();
    }

//    @PostMapping
//    public String createEmployee(){
//        return "Employee created";
//    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id){
        return employeeService.updateEmployeeById(employeeDTO,id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployeeById(@PathVariable Long id){
        return employeeService.deleteEmployeeById(id);
    }

    @PatchMapping("/{id}")
    public EmployeeDTO updatePartialEmployeeById(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        return employeeService.updatePartialEmployeeById(id, updates);
    }



}
