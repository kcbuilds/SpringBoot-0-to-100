package org.example.controller;

import org.example.dto.EmployeeDTO;
import org.example.entities.EmployeeEntity;
import org.example.repositories.EmployeeRepository;
import org.example.services.EmployeeService;
import org.springframework.http.HttpStatus;
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

//        if (employeeDTO.isPresent()) {
//            return ResponseEntity.ok().body(employeeDTO.get());
//        }else {
//            return ResponseEntity.notFound().build();
//        }

//        return ResponseEntity.ok().body(employeeDTO.get());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(required = false, name = "myAge") Integer age,
                                             @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

//    @PostMapping
//    public String createEmployee(){
//        return "Employee created";
//    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO employeeDTO = employeeService.createNewEmployee(inputEmployee)
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long id){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long id){
        boolean gotDeleted = employeeService.deleteEmployeeById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(id, updates);

        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }



}
