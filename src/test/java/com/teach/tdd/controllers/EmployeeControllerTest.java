package com.teach.tdd.controllers;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teach.tdd.models.Employee;
import com.teach.tdd.services.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmployeeService employeeService;

	@Test
	public void testGetEmployees() throws Exception {

		// given
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee());

		given(employeeService.findEmployees()).willReturn(employees);

		// when
		mockMvc.perform(get("/employees")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));

	}

	@Test
	public void testAddEmployees() throws Exception {

		// given
		Employee e1 = new Employee();
		e1.setFirstName("Selvakumar");
		e1.setLastName("Ponnusamy");
		String json = new ObjectMapper().writeValueAsString(e1);
		
		e1.setId(UUID.randomUUID());
		given(employeeService.addEmployee(ArgumentMatchers.any(Employee.class))).willReturn(e1);

		// when
		mockMvc.perform(post("/employees").content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$", hasKey("id")));

	}

}
