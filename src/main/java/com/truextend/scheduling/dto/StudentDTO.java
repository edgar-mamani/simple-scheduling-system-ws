package com.truextend.scheduling.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public class StudentDTO {
	
	private Integer id;
	
	@NotEmpty(message = "Please provide a first name")
	@Max(value = 100, message = "Length maximum allow is 100 characters")
	private String firstName;
	
	@NotEmpty(message = "Please provide a last name")
	@Max(value = 100, message = "Length maximum allow is 100 characters")
	private String lastName;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDTO other = (StudentDTO) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

}
