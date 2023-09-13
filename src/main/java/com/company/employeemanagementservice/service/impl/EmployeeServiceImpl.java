package com.company.employeemanagementservice.service.impl;

import com.company.employeemanagementservice.dto.EmployeeDto;
import com.company.employeemanagementservice.entity.Employee;
import com.company.employeemanagementservice.exceptions.EmailUniqenessException;
import com.company.employeemanagementservice.exceptions.EmployeeNotFound;
import com.company.employeemanagementservice.repository.EmployeeRepository;
import com.company.employeemanagementservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        var employeeExist = employeeRepository
                .findEmployeeByEmail(employeeDto.getEmail())
                .isPresent();
        if (!employeeExist) {
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
        }else {
            throw new EmailUniqenessException("Email must be unique.");
        }
    }

    @Override
    public boolean updateEmployee(Long employeeId, EmployeeDto employeeDto) {

        var employeeExist = employeeRepository.findById(employeeId).isPresent();
        if(employeeExist){
            var optEmployee = employeeRepository.findById(employeeId);
            Employee employeeToUpdate = optEmployee.get();
            employeeToUpdate.setName(employeeDto.getName());
            employeeToUpdate.setSurname(employeeDto.getSurname());
            employeeToUpdate.setEmail(employeeDto.getEmail());

            modelMapper.map(employeeRepository.save(optEmployee.get()), EmployeeDto.class);
            return true;
        }else{
            //Employee employee = optEmployee.orElseThrow(() -> new EmployeeNotFound());
            throw new EmployeeNotFound("Employee not found with this id.");
        }
        //throw new EmployeeNotFound("There are no employee with id: " + employeeId);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Optional<Employee> optEmployee = employeeRepository.findById(employeeId);
        if (optEmployee.isPresent()) {
            return modelMapper.map(optEmployee.get(), EmployeeDto.class);
        }
        throw new EmployeeNotFound("Employee not found with this id.");

    }

    @Override
    public List<EmployeeDto> getEmployeeList() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public boolean deleteEmployee(Long employeeId) {
        Optional<Employee> optEmployee = employeeRepository.findById(employeeId);
        if (optEmployee.isPresent()) {
            this.employeeRepository.deleteById(employeeId);
            return true;
        }
        throw new EmployeeNotFound("Employee not found with this id.");
    }
}
