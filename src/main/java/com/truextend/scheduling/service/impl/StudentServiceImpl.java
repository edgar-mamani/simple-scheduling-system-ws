package com.truextend.scheduling.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.entity.Student;
import com.truextend.scheduling.repository.StudentRepository;
import com.truextend.scheduling.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Cacheable(value = "students", key = "#studentId")
	@Override
	public Student getStudentById(Integer studentId) {
		return studentRepository.findById(studentId).orElse(null);
	}

	@CachePut(value = "students", key = "#student.id")
	@Override
	public void updateStudent(Student student) {
		studentRepository.save(student);
	}

	@CacheEvict(value = "students", key = "#studentId")
	@Override
	public void deleteStudent(Integer studentId) {
		studentRepository.deleteById(studentId);
	}

	@Override
	public Iterable<Student> getAllStudents() {
		return studentRepository.findAll();
	}

}
