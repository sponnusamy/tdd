package com.learn.tdd.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.tdd.models.Department;
import com.learn.tdd.models.Employee;
import com.learn.tdd.services.DepartmentService;

@RestController
@RequestMapping("/depts")
public class DepartmentController {

	@Autowired
	DepartmentService deptService;

	@RequestMapping(produces = "application/json")
	public List<Department> getDepts() {
		return deptService.getDepts();
	}

	@RequestMapping(value = "{id}/employees", produces = "application/json")
	public Set<Employee> getEmployeesInDept(@PathVariable("id") UUID id) {
		return deptService.getEmployeesInDept(id);
	}

	@RequestMapping(value = "{id}", produces = "application/json")
	public Department getDept(@PathVariable("id") UUID id) {
		return deptService.getDept(id);
	}
}
