package com.learn.tdd.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.learn.tdd.models.Employee;
import com.learn.tdd.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

	@TestConfiguration
	static class EmployeeServiceTestContextConfiguration {

		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@Test
	public void testFindEmployees() {
		// given
		List<Employee> list = new ArrayList<>();
		list.add(new Employee());

		given(employeeRepository.findAll()).willReturn(list);

		// when
		List<Employee> res = employeeService.findEmployees();

		// then
		assertEquals(1, res.size());

	}

	@Test
	public void testAddEmployee() {
		// given
		Employee e1 = new Employee();
		e1.setFirstName("Selvakumar");

		given(employeeRepository.save(e1)).willReturn(e1);

		// when
		Employee res = employeeService.addEmployee(e1);

		// then
		assertEquals(e1.getFirstName(), res.getFirstName());
		assertNotNull(res.getId());

	}
}
