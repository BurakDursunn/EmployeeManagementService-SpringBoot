package com.company.employeemanagementservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String bankAccountNumber;
}
