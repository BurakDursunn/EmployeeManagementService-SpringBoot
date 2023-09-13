package com.company.employeemanagementservice.controller;

import com.company.employeemanagementservice.dto.EmployeeDto;
import com.company.employeemanagementservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @Operation(summary = "Create employee.", description = "Create employee from body section.")
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        var createdEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployeeDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update employee.", description = "Update employee from body section.")
    @PutMapping("/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employeeDto) {
        employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseEntity<>("Employee is updated.", HttpStatus.OK);
    }

    @Operation(summary = "Get employee.", description = "Get employee from desired id.")
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDto newEmployeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(newEmployeeDto, HttpStatus.OK);
    }

    @Operation(summary = "Get all employees.", description = "Get All employees.")
    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getEmployeeList() {
        List<EmployeeDto> newEmployeeDtoList = employeeService.getEmployeeList();
        return new ResponseEntity<>(newEmployeeDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Delete employee.", description = "Delete desired employee.")
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee is deleted.", HttpStatus.OK);
    }
}
