package com.ibm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibm.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	List<Student> getStudentsByName(String name);
	
}
