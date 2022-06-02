package com.truextend.scheduling.dto;

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

}
