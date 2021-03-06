package com.truextend.scheduling.dto;

import java.util.Objects;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "AvailabilityInfo")
public class AvailabilityInfoDTO {

	private Integer studentId;
	private String classCode;
	private boolean availability;
	
	public AvailabilityInfoDTO() {}
	
	public AvailabilityInfoDTO(Integer studentId, String classCode) {
		this.studentId = studentId;
		this.classCode = classCode;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availability, classCode, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvailabilityInfoDTO other = (AvailabilityInfoDTO) obj;
		return availability == other.availability && Objects.equals(classCode, other.classCode)
				&& Objects.equals(studentId, other.studentId);
	}
}
