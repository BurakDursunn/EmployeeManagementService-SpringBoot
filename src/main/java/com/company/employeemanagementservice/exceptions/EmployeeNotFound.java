package com.company.employeemanagementservice.exceptions;

public class EmployeeNotFound extends RuntimeException{
    public EmployeeNotFound(String message) {
        super(message);
    }


}
