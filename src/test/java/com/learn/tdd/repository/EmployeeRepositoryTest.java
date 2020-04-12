package com.learn.tdd.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.learn.tdd.models.Employee;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void testFindAll() {
		Employee e1 = new Employee();
		e1.setId(UUID.randomUUID());
		
		employeeRepository.save(e1);
		

		List<Employee> employees = employeeRepository.findAll();

		assertTrue(employees.size() == 1);
	}

}
