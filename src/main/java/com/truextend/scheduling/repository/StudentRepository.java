package com.truextend.scheduling.repository;

import org.springframework.data.repository.CrudRepository;

import com.truextend.scheduling.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
