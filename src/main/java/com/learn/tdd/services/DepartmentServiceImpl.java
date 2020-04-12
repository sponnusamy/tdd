package com.learn.tdd.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.tdd.exceptions.ResourceNotFoundException;
import com.learn.tdd.models.Department;
import com.learn.tdd.models.Employee;
import com.learn.tdd.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository deptRepository;

	@Override
	public List<Department> getDepts() {
		return deptRepository.findAll();
	}

	@Override
	public Department addDept(Department department) {
		return deptRepository.save(department);
	}

	@Override
	public Department getDept(UUID id) {
		Optional<Department> o = deptRepository.findById(id);
		if (!o.isPresent()) {
			throw new ResourceNotFoundException("Dept couldn't found for the id " + id);
		}
		return o.get();
	}

	@Override
	public Set<Employee> getEmployeesInDept(UUID id) {
		Department d = getDept(id);
		return d.getEmployees();
	}

}
