package com.teach.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.teach.tdd.models.Department;
import com.teach.tdd.models.Employee;
import com.teach.tdd.services.DepartmentService;
import com.teach.tdd.services.EmployeeService;

@SpringBootTest(classes = TddApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class DepartmentControllerIT {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	int port;

	@Autowired
	DepartmentService deptService;

	@Autowired
	EmployeeService employeeService;

	Supplier<String> host = () -> String.format("http://localhost:%s", port);

	@Test
	public void testDepartments() {

		Department d1 = new Department();
		d1.setId(UUID.randomUUID());
		d1.setName("Accounts");
		d1.setColor("blue");

		deptService.addDept(d1);

		ResponseEntity<Department[]> response = restTemplate.getForEntity(host.get() + "/depts", Department[].class);

		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().length > 0);

		List<Department> depts = new ArrayList<>(Arrays.asList(response.getBody()));

		assertTrue(depts.contains(d1));

	}

	@Test
	public void testDepartmentById() {

		Department d1 = new Department();
		d1.setId(UUID.randomUUID());
		d1.setName("Accounts");
		d1.setColor("blue");

		deptService.addDept(d1);
		
		ResponseEntity<Department> response = restTemplate
				.getForEntity(String.format("%s/depts/%s", host.get(), d1.getId()), Department.class);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
		assertEquals(d1.getId(), response.getBody().getId());

	}

	@Test
	public void testDepartmentByIdNotFound() {

		ResponseEntity<Department> response = restTemplate
				.getForEntity(String.format("%s/depts/%s", host.get(), UUID.randomUUID()), Department.class);

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());

	}

	@Test
	public void testEmployeesInDept() {
		Department d1 = new Department();
		d1.setId(UUID.randomUUID());
		d1.setName("Accounts");
		d1.setColor("blue");

		deptService.addDept(d1);

		Employee e1 = new Employee();
		e1.setId(UUID.randomUUID());
		e1.setDept(d1);

		employeeService.addEmployee(e1);

		ResponseEntity<Employee[]> response = restTemplate
				.getForEntity(String.format("%s/depts/%s/employees", host.get(), d1.getId()), Employee[].class);

		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
		Employee[] employees = response.getBody();

		assertTrue(employees[0].getId().equals(e1.getId()));

	}
}
