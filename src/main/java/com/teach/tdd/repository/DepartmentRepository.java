package com.teach.tdd.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teach.tdd.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
