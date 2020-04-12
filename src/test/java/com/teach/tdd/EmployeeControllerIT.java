package com.teach.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teach.tdd.models.Employee;
import com.teach.tdd.services.EmployeeService;

@SpringBootTest(classes = TddApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	EmployeeService employeeService;

	@LocalServerPort
	int port;

	Supplier<String> host = () -> String.format("http://localhost:%s", port);

	@Test
	public void testEmployees() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(host.get() + "/employees", String.class);

		assertEquals(200, response.getStatusCode().value());
		
		System.out.println(response.getBody());

	}
	
	@Test
	public void testAddEmployees() throws JsonProcessingException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Employee e1 = new Employee();
		e1.setFirstName("Selvakumar");
		e1.setLastName("Ponnusamy");

		String json = new ObjectMapper().writeValueAsString(e1);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);
		
		ResponseEntity<Employee> response = restTemplate.postForEntity(host.get() + "/employees", entity, Employee.class);

		assertEquals(201, response.getStatusCode().value());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());

	}
}
