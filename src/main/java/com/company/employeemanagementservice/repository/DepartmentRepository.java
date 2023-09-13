package com.company.employeemanagementservice.repository;

import com.company.employeemanagementservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
