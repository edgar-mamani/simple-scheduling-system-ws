package com.truextend.scheduling.repository;

import org.springframework.data.repository.CrudRepository;

import com.truextend.scheduling.entity.Course;

public interface CourseRepository extends CrudRepository<Course, String> {

}
