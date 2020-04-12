package com.learn.tdd.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = -2634560101280542279L;

	@Id
	private UUID id;

	@Column(name = "first_name")
	private String firstName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dept_id")
	private Department dept;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "last_name")
	private String lastName;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Employee))
			return false;
		
		Employee that = (Employee) obj;
				
		return this.getId().equals(that.getId());
	}

}
