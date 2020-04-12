package com.learn.tdd.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.learn.tdd.models.Department;

@DataJpaTest
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository deptRepository;

	@Test
	public void tetsFindAll() {
		Department d1 = new Department();
		d1.setId(UUID.randomUUID());
		d1.setName("Accounts");

		deptRepository.save(d1);

		List<Department> depts = deptRepository.findAll();

		assertTrue(depts.size() > 0);
	}
}
