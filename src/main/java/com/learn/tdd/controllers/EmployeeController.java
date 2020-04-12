package com.learn.tdd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.tdd.models.Employee;
import com.learn.tdd.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(produces = "application/json", method = RequestMethod.GET)
	public List<Employee> getEmployees() {
		return employeeService.findEmployees();

	}

	@RequestMapping(produces = "application/json", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);

	}
}
