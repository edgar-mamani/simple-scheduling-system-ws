package com.truextend.scheduling.service;

import java.util.List;

import com.truextend.scheduling.dto.AvailabilityInfoDTO;
import com.truextend.scheduling.dto.StudentDTO;

public interface StudentService {

	StudentDTO createStudent(StudentDTO student);

	StudentDTO getStudentById(Integer studentId);

	void updateStudent(StudentDTO student);

	void deleteStudent(Integer studentId);

	List<StudentDTO> getAllStudents();

	List<StudentDTO> getStudentsByCourse(String courseCode);

	AvailabilityInfoDTO checkAvailability(Integer studentId, String courseCode);

}
