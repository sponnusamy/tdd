package com.teach.tdd;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.teach.tdd.controllers.DepartmentController;
import com.teach.tdd.models.Department;
import com.teach.tdd.models.Employee;
import com.teach.tdd.services.DepartmentService;

@WebMvcTest(controllers = DepartmentController.class)
public class DepartmentControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private DepartmentService deptService;

	@Test
	public void testDepts() throws Exception {
		List<Department> depts = new ArrayList<>();
		depts.add(new Department());

		given(deptService.getDepts()).willReturn(depts);
		mockMvc.perform(get("/depts")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));

	}

	@Test
	public void testDept() throws Exception {
		Department d = new Department();
		d.setId(UUID.randomUUID());

		given(deptService.getDept(d.getId())).willReturn(d);
		mockMvc.perform(get("/depts/"+d.getId())).andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(d.getId().toString())));

	}

	@Test
	public void testGetEmployeesByDept() throws Exception {

		Employee e1 = new Employee();
		Employee e2 = new Employee();

		Set<Employee> employees = new HashSet<Employee>();
		employees.add(e1);
		employees.add(e2);

		UUID id = UUID.randomUUID();

		given(deptService.getEmployeesInDept(id)).willReturn(employees);

		mockMvc.perform(get(String.format("/depts/%s/employees", id))).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));

	}

}
