package org.example.controller;

import org.example.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") int id) {
        return new EmployeeDTO(id, "Karan", "kc@gmail.com", 24, LocalDate.of(2026, 05, 07), true);
    }

    @GetMapping
    public String getEmployees(@RequestParam(required = false, name = "myAge") Integer age,
                               @RequestParam(required = false) String sortBy) {
        return "My age is " + age + " sort by " + sortBy;
    }

}
