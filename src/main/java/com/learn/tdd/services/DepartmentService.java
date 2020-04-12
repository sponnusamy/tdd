package com.learn.tdd.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.learn.tdd.models.Department;
import com.learn.tdd.models.Employee;

@Service
public interface DepartmentService {

	List<Department> getDepts();

	Department addDept(Department department);

	Department getDept(UUID id);

	Set<Employee> getEmployeesInDept(UUID id);

}
