package com.truextend.scheduling.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TEXT_STUDENT_COURSE")
public class Enrollment {

	@EmbeddedId
	private Id id;
	
	public Enrollment() {}
	
	public Enrollment(Integer studentId, String courseCode) {
		this.id = new Id(studentId, courseCode);
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		return Objects.equals(id, other.id);
	}

	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = -2176245498963585167L;

		@Column(name = "student_id")
		private Integer studentId;
		
		@Column(name = "course_code")
		private String courseCode;
		
		public Id() {}
		
		public Id(Integer studentId, String courseCode) {
			this.studentId = studentId;
			this.courseCode = courseCode;
		}

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
			Id other = (Id) obj;
			return Objects.equals(courseCode, other.courseCode) && Objects.equals(studentId, other.studentId);
		}
	}
}
