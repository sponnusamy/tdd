package com.teach.tdd.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teach.tdd.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

}
