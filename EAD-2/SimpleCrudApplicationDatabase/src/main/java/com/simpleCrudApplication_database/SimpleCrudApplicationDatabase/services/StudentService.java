package com.simpleCrudApplication_database.SimpleCrudApplicationDatabase.services;

import com.simpleCrudApplication_database.SimpleCrudApplicationDatabase.model.Student;
import com.simpleCrudApplication_database.SimpleCrudApplicationDatabase.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
private final StudentRepository studentRepository;

@Autowired
public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

}
