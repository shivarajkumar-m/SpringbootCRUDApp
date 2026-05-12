package com.ibm.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.dto.StudentDTO;
import com.ibm.entity.Student;
import com.ibm.exception.BadRequestException;
import com.ibm.exception.StudentIdNotFound;
import com.ibm.repository.StudentRepository;
@Service
public class StudentService {
	@Autowired
	private StudentRepository sr;
	
//	List<Student> students=new ArrayList<>();
//	
//	public StudentService() {
//		students.add(new Student(1,"Raj","raj@gmail.com"));
//		students.add(new Student(2,"Ram","ram@gmail.com"));
//		students.add(new Student(3,"Ravi","ravi@gmail.com"));
//		students.add(new Student(4,"SRK","srk@gmail.com"));
//	}
//
//	public List<Student> getAllStudents() {
//		return students;
//	}
//
//	public Student getStudentById(int id) {
//		for(Student s:students) {
//			if(s.getId()==id) {
//				return s;
//			}
//		}
//		return null;
//
//	}

//	public String delete(int id) {
//		students.removeIf(s->s.getId()==id);
//		return "Students deleted successfully";
//		
//	}
//
//	public String editStudent(int id, Student s) {
//		for(Student st:students) {
//			if(st.getId()==id) {
//				if(s.getName() != null) {
//					st.setName(s.getName());
//					}
//				if(s.getEmail()!=null) {
//					st.setEmail(s.getEmail());
//				}
//				}
//		}
//		return "Student Updated Successfully";
//	}
	
//	public void save(Student s) {
//		sr.save(s);
//		
//	}
//	
//	public List<Student> getAll() {
//		return sr.getAll();
//	}
//
//	public void delete(int id) {
//		sr.delete(id);
//		
//	}
	
//	public String addStudent(Student s) {
//		if(s.getName()!=null && s.getEmail()!=null) {
//		sr.save(s);
//		return "Student added successfully";
//		}
//		else {
//			return null;
//		}
//	}
	@Autowired
	public ModelMapper mapper;
	
	public String addStudent(StudentDTO s) {
		Student student=mapper.map(s, Student.class);
	    sr.save(student);
	    return "Student added successfully";
	}
	
	public List<StudentDTO> getAll() {
		if(sr.findAll()!=null) {
			List<Student> list = (List<Student>) sr.findAll();
			List<StudentDTO> dtos = new ArrayList<>();
			for(Student i:list) {
				dtos.add(new StudentDTO(i.getId(), i.getName(), i.getEmail()));
			}
			return dtos;
		}
		else {
			return null;
		}
	}

	public String delete(int id) throws BadRequestException {
		Student opt = sr.findById(id).orElseThrow(()->new BadRequestException("Bad request Student not found with id "+id));
	        sr.deleteById(id);
			return "Student deleted successfully";

	}

	public StudentDTO getStudentById(Integer id) throws StudentIdNotFound {

	    Student student = sr.findById(id).orElseThrow(() -> new StudentIdNotFound("Student not found with id: " + id));

	    // Convert Entity → DTO
	    StudentDTO dto = mapper.map(student, StudentDTO.class);
	    dto.setName(student.getName());
	    dto.setEmail(student.getEmail());

	    return dto;
	}
	
	public StudentDTO updateStudentById(Integer id, StudentDTO dto) {

	    Student existStudent = sr.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
	    existStudent.setName(dto.getName());
	    existStudent.setEmail(dto.getEmail());

	    Student updated = sr.save(existStudent);
	    StudentDTO result = mapper.map(updated, StudentDTO.class);
	    result.setName(updated.getName());
	    result.setEmail(updated.getEmail());
	    return result;
	}
	
	public List<Student> getStudentsByName(String name) {
	    
		return sr.getStudentsByName(name);
	}


}
