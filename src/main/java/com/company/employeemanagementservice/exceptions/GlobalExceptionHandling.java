package com.company.employeemanagementservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errorMap = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(erd -> {
            String errorField = ((FieldError) erd).getField();
            String errorMessage = erd.getDefaultMessage();
            errorMap.put(errorField, errorMessage);
        });
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailUniqenessException.class)
    public ResponseEntity<ExceptionResponse> mailException(Exception exception, WebRequest webRequest) {
        var exceptionResponse = new ExceptionResponse(exception.getMessage(),HttpStatus.EXPECTATION_FAILED, LocalDateTime.now());
        //exceptionResponse.setUrl();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<ExceptionResponse> employeeException(Exception exception, WebRequest webRequest) {
        var exceptionResponse = new ExceptionResponse(exception.getMessage(),HttpStatus.EXPECTATION_FAILED,LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> departmentNotFoundException(Exception exception, WebRequest webRequest) {
        var exceptionResponse = new ExceptionResponse(exception.getMessage(),HttpStatus.EXPECTATION_FAILED,LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }
}
