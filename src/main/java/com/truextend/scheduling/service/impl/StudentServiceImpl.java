package com.truextend.scheduling.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.truextend.scheduling.dto.AvailabilityInfoDTO;
import com.truextend.scheduling.dto.StudentDTO;
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
	public StudentDTO createStudent(StudentDTO studentDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Student student = modelMapper.map(studentDTO, Student.class);
		
		student = studentRepository.save(student);
		
		return modelMapper.map(student, StudentDTO.class);
	}

	@Cacheable(value = "students", key = "#studentId")
	@Override
	public StudentDTO getStudentById(Integer studentId) {
		Optional<Student> optStudent = studentRepository.findById(studentId);
		
		if (!optStudent.isPresent()) throw new EntityNotFoundException(Student.class, "studentId", studentId.toString());
		
		ModelMapper modelMapper = new ModelMapper();
		
		return modelMapper.map(optStudent.get(), StudentDTO.class);
	}

	@CachePut(value = "students", key = "#student.id")
	@Override
	public void updateStudent(StudentDTO studentDTO) {
		getStudentById(studentDTO.getId());
		
		ModelMapper modelMapper = new ModelMapper();
		
		studentRepository.save(modelMapper.map(studentDTO, Student.class));
	}

	@CacheEvict(value = "students", key = "#studentId")
	@Override
	public void deleteStudent(Integer studentId) {
		getStudentById(studentId);
		studentRepository.deleteById(studentId);
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		Iterable<Student> students = studentRepository.findAll();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<StudentDTO> response = new ArrayList<>(0);
		
		students.forEach(s -> response.add(modelMapper.map(s, StudentDTO.class)));
		
		return response;
	}

	@Override
	public List<StudentDTO> getStudentsByCourse(String courseCode) {
		List<Student> students = studentRepository.findStudentsByCourse(courseCode);
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<StudentDTO> response = new ArrayList<>(0);
		
		students.stream().forEach(s -> response.add(modelMapper.map(s, StudentDTO.class)));
		
		return response;
	}
	
	@Override
	public AvailabilityInfoDTO checkAvailability(Integer studentId, String courseCode) {
		Optional<Enrollment> result = enrollmentRepository.checkAvailabilityByStudentIdAndCourseCode(studentId, courseCode);
		
		AvailabilityInfoDTO info = new AvailabilityInfoDTO(studentId, courseCode);
		info.setAvailability(result.isPresent());
		
		return info;
	}
	
}
