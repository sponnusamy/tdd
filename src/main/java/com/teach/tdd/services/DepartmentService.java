package com.teach.tdd.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teach.tdd.models.Department;
import com.teach.tdd.models.Employee;

@Service
public interface DepartmentService {

	List<Department> getDepts();

	Department addDept(Department department);

	Department getDept(UUID id);

	Set<Employee> getEmployeesInDept(UUID id);

}
