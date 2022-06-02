package com.truextend.scheduling.dto;

import java.util.Objects;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Enrollment")
public class EnrollmentDTO {
	private Integer studentId;
	private String courseCode;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseCode, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnrollmentDTO other = (EnrollmentDTO) obj;
		return Objects.equals(courseCode, other.courseCode) && Objects.equals(studentId, other.studentId);
	}

}
