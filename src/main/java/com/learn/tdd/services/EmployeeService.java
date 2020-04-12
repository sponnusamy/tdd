package com.learn.tdd.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.tdd.models.Employee;

@Service
public interface EmployeeService {

	public List<Employee> findEmployees() ;

	public Employee addEmployee(Employee employee);

}
