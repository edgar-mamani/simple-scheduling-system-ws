package com.truextend.scheduling.service;

import com.truextend.scheduling.entity.Student;

public interface StudentService {

	Student createStudent(Student student);

	Student getStudentById(Integer studentId);

	void updateStudent(Student student);

	void deleteStudent(Integer studentId);

	Iterable<Student> getAllStudents();

}
