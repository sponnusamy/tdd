package com.teach.tdd.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teach.tdd.exceptions.ResourceNotFoundException;
import com.teach.tdd.models.Department;
import com.teach.tdd.models.Employee;
import com.teach.tdd.repository.DepartmentRepository;

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
