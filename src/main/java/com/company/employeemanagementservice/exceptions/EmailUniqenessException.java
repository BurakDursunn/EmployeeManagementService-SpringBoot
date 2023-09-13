package com.company.employeemanagementservice.exceptions;

public class EmailUniqenessException extends RuntimeException {
    public EmailUniqenessException(String message) {
        super(message);
    }
}
