package com.truextend.scheduling.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.dto.AvailabilityInfo;
import com.truextend.scheduling.entity.Enrollment;
import com.truextend.scheduling.entity.Student;
import com.truextend.scheduling.exception.EntityNotFoundException;
import com.truextend.scheduling.repository.EnrollmentRepository;
import com.truextend.scheduling.repository.StudentRepository;
import com.truextend.scheduling.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Cacheable(value = "students", key = "#studentId")
	@Override
	public Student getStudentById(Integer studentId) {
		Optional<Student> optStudent = studentRepository.findById(studentId);
		
		if (!optStudent.isPresent()) throw new EntityNotFoundException(Student.class, "studentId", studentId.toString());
		
		return optStudent.get();
	}

	@CachePut(value = "students", key = "#student.id")
	@Override
	public void updateStudent(Student student) {
		getStudentById(student.getId());
		studentRepository.save(student);
	}

	@CacheEvict(value = "students", key = "#studentId")
	@Override
	public void deleteStudent(Integer studentId) {
		getStudentById(studentId);
		studentRepository.deleteById(studentId);
	}

	@Override
	public Iterable<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> getStudentsByCourse(String courseCode) {
		return studentRepository.findStudentsByCourse(courseCode);
	}
	
	@Override
	public AvailabilityInfo checkAvailability(Integer studentId, String courseCode) {
		Optional<Enrollment> result = enrollmentRepository.checkAvailabilityByStudentIdAndCourseCode(studentId, courseCode);
		
		AvailabilityInfo info = new AvailabilityInfo(studentId, courseCode);
		info.setAvailability(result.isPresent());
		
		return info;
	}
	
}
