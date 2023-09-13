package com.company.employeemanagementservice.service.impl;

import com.company.employeemanagementservice.dto.DepartmentDto;
import com.company.employeemanagementservice.dto.EmployeeDto;
import com.company.employeemanagementservice.entity.Department;
import com.company.employeemanagementservice.entity.Employee;
import com.company.employeemanagementservice.exceptions.DepartmentNotFoundException;
import com.company.employeemanagementservice.repository.DepartmentRepository;
import com.company.employeemanagementservice.repository.EmployeeRepository;
import com.company.employeemanagementservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);

        return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
    }

    @Override
    public boolean updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        var optDepartment = departmentRepository.findById(departmentId);
        Department departmentToUpdate = optDepartment.get();
        departmentToUpdate.setName(departmentDto.getName());
        modelMapper.map(departmentRepository.save(optDepartment.get()), DepartmentDto.class);
        return true;
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Optional<Department> optDepartment = departmentRepository.findById(departmentId);

        if (optDepartment.isPresent()) {
            return modelMapper.map(optDepartment.get().getEmployees(), DepartmentDto.class);
        }
        throw new DepartmentNotFoundException("There is no department.");
    }

    @Override
    public List<DepartmentDto> getDepartmentList() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentsDtos = departments.stream().map(department -> modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
        return departmentsDtos;
    }

    @Override
    public boolean deleteDepartment(Long departmentId) {
        Optional<Department> optDepartment = departmentRepository.findById(departmentId);
        if (optDepartment.isPresent()) {
            this.departmentRepository.deleteById(departmentId);
            return true;
        }
        throw new DepartmentNotFoundException("There is no department.");
    }
}
