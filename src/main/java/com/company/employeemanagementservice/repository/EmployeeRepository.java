package com.company.employeemanagementservice.repository;

import com.company.employeemanagementservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByEmail(String email);
    Optional<Employee> findEmployeeById(Long id);
}
