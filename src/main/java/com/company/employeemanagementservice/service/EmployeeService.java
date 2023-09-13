package com.company.employeemanagementservice.service;

import com.company.employeemanagementservice.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    boolean updateEmployee(Long employeeId, EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getEmployeeList();
    boolean deleteEmployee(Long employeeId);
}
