package com.ibm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ibm.entity.Student;

@Repository
public class StudentRepo {

    private List<Student> students = new ArrayList<>();

    public void save(Student s) {
        students.add(s);
        System.out.println("Students List: " + students);
    }

    public List<Student> getAll() {
        return students;
    }

    public void delete(int id) {
        students.removeIf(s -> s.getId() == id);
    }
}