package com.truextend.scheduling.service;

import java.util.List;

import com.truextend.scheduling.entity.Student;

public interface StudentService {

	Student createStudent(Student student);

	Student getStudentById(Integer studentId);

	void updateStudent(Student student);

	void deleteStudent(Integer studentId);

	Iterable<Student> getAllStudents();

	List<Student> getStudentsByCourse(String courseCode);

}
