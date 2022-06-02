package com.truextend.scheduling.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.truextend.scheduling.dto.StudentDTO;
import com.truextend.scheduling.entity.Student;
import com.truextend.scheduling.exception.EntityNotFoundException;
import com.truextend.scheduling.repository.StudentRepository;
import com.truextend.scheduling.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	@Test
	public void givenValidCourseCode_whenSearch_shouldReturnCourse() {
		
		Integer validStudentId = 111;
		
		var studentDTO = new StudentDTO();
		studentDTO.setId(validStudentId);
		
		var student = new Student();
		student.setId(validStudentId);
		
		given(studentRepository.findById(validStudentId)).willReturn(Optional.of(student));
		
		var result = studentService.getStudentById(validStudentId);
		
		assertEquals(result.getId(), studentDTO.getId());
	}
	
	@Test
	public void givenNotValidCourseCode_whenSearch_shouldThrowException() {
		
		Integer noValidStudentId = 111;
		
		given(studentRepository.findById(noValidStudentId)).willReturn(Optional.empty());
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			studentService.getStudentById(noValidStudentId);
		});
	}
}
