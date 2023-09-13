package com.company.employeemanagementservice.controller;


import com.company.employeemanagementservice.dto.DepartmentDto;
import com.company.employeemanagementservice.dto.EmployeeDto;
import com.company.employeemanagementservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;


    @Operation(summary = "Create department.", description = "Create department from body section.")
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        var createdDepartmentDto = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(createdDepartmentDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update department.", description = "Update department from body section.")
    @PutMapping("/{departmentId}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long departmentId, @RequestBody DepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentId, departmentDto);
        return new ResponseEntity<>("Department is updated.", HttpStatus.OK);
    }

    @Operation(summary = "Get department.", description = "Get department from desired id.")
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long departmentId) {
        DepartmentDto newDepartmentDto = departmentService.getDepartmentById(departmentId);
        return new ResponseEntity<>(newDepartmentDto, HttpStatus.OK);
    }

    @Operation(summary = "Get all departments.", description = "Get All departments.")
    @GetMapping("/getAll")
    public ResponseEntity<List<DepartmentDto>> getDepartmentList() {
        List<DepartmentDto> newDepartmentDtoList = departmentService.getDepartmentList();
        return new ResponseEntity<>(newDepartmentDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Delete department.", description = "Delete desired department.")
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>("Department is deleted.", HttpStatus.OK);
    }
}
