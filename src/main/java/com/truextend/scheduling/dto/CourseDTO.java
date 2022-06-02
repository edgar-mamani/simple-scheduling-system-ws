package com.truextend.scheduling.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Course")
public class CourseDTO {
	@NotEmpty(message = "Please provide a code")
	@Size(max = 20, message = "Length maximum allow is 20 characters")
	private String code;
	
	@NotEmpty(message = "Please provide a title")
	@Size(max = 50, message = "Length maximum allow is 50 characters")
	private String title;
	
	@NotEmpty(message = "Please provide a description")
	@Size(max = 255, message = "Length maximum allow is 255 characters")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, description, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseDTO other = (CourseDTO) obj;
		return Objects.equals(code, other.code) && Objects.equals(description, other.description)
				&& Objects.equals(title, other.title);
	}

}
