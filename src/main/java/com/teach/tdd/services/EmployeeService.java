package com.teach.tdd.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teach.tdd.models.Employee;

@Service
public interface EmployeeService {

	public List<Employee> findEmployees() ;

	public Employee addEmployee(Employee employee);

}
