package com.ibm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.dto.StudentDTO;
import com.ibm.entity.Student;
import com.ibm.exception.BadRequestException;
import com.ibm.exception.StudentIdNotFound;
import com.ibm.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/student")
@Tag(name ="Student Controller", description="it is used to manage Student object api's")
public class HelloController {
	 
	@Autowired
	private StudentService service;
	
	@PostMapping("/add")
	@Operation(summary = "Add New Student")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Student added successfully"),
		@ApiResponse(responseCode = "400", description="Student not added")
	})
	public ResponseEntity<String> addStudent(	 @RequestBody StudentDTO s) {
		String result = service.addStudent(s);
		System.out.println("Controller executed");

	    return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudentByID(@PathVariable Integer id) throws StudentIdNotFound {

	    StudentDTO s = service.getStudentById(id);

	    return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws BadRequestException{
        String s=service.delete(id);
       
        return new ResponseEntity<String>(s, HttpStatus.OK);
        
    }
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@Valid
	        @RequestBody StudentDTO s,
	        @PathVariable int id) {

	    StudentDTO st = service.updateStudentById(id, s);

	    return new ResponseEntity<>(st, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StudentDTO>> getStudents(){
		List<StudentDTO> s=service.getAll();
		if(!s.isEmpty()) {
			return new ResponseEntity<List<StudentDTO>>(s,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<StudentDTO>>(s,HttpStatus.NO_CONTENT);
			
		}
	}
	
	@GetMapping("/name/{stname}")
	public ResponseEntity<List<Student>> getByName(@PathVariable String stname) {

	    List<Student> list = service.getStudentsByName(stname);

	    if(!list.isEmpty()) {
	        return new ResponseEntity<>(list, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
}
