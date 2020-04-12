package com.learn.tdd.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.tdd.exceptions.ResourceNotFoundException;
import com.learn.tdd.models.Department;
import com.learn.tdd.models.Employee;
import com.learn.tdd.repository.DepartmentRepository;

@ExtendWith(SpringExtension.class)
public class DepartmentServiceTest {
	@TestConfiguration
	static class MyTestConfiguration {

		@Bean
		DepartmentService departmentService() {
			return new DepartmentServiceImpl();
		}
	}

	@Autowired
	DepartmentService deptService;

	@MockBean
	DepartmentRepository deptRepository;

	@Test
	public void testGetDepts() {
		List<Department> depts = new ArrayList<Department>();
		depts.add(new Department());

		given(deptRepository.findAll()).willReturn(depts);

		List<Department> res = deptService.getDepts();

		assertTrue(res != null && res.size() > 0);
	}

	@Test
	public void testGetDept() {
		Department d = new Department();
		d.setId(UUID.randomUUID());

		given(deptRepository.save(any(Department.class))).willReturn(d);

		Department res = deptService.addDept(d);

		assertTrue(res != null && res.getId().equals(d.getId()));
	}

	@Test
	public void testGetDeptById() {
		Department d = new Department();
		d.setId(UUID.randomUUID());

		UUID id = UUID.randomUUID();

		given(deptRepository.findById(id)).willReturn(Optional.of(d));

		Department res = deptService.getDept(id);

		assertTrue(res != null && res.getId().equals(d.getId()));
	}

	@Test
	public void testGetDeptById_NotFound() {
		assertThrows(ResourceNotFoundException.class, () -> {
			deptService.getDept(UUID.randomUUID());
		});
	}

	@Test
	public void testGetEmployeesInDept() {
		// given
		Department d1 = new Department();
		d1.setId(UUID.randomUUID());
		Set<Employee> employees = new HashSet<Employee>();
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		employees.add(e1);
		employees.add(e2);
		d1.setEmployees(employees);

		given(deptRepository.findById(d1.getId())).willReturn(Optional.of(d1));

		// when
		Set<Employee> res = deptService.getEmployeesInDept(d1.getId());

		// then
		assertTrue(res != null && res.size() == 2);
	}

}
