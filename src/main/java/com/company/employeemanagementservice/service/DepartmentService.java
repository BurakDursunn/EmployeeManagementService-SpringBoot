package com.company.employeemanagementservice.service;

import com.company.employeemanagementservice.dto.DepartmentDto;
import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    boolean updateDepartment(Long departmentId, DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long departmentId);
    List<DepartmentDto> getDepartmentList();
    boolean deleteDepartment(Long departmentId);
}
