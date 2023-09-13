package com.company.employeemanagementservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Name must not be blank.")
    private String name;
    @NotBlank(message = "Surname must not be blank.")
    private String surname;
    @Column(nullable = false, unique = true)
    @Email(message = "Email-Trying")
    @NotBlank(message = "Email must not be blank.")
    private String email;
    private DepartmentDto department;
}
