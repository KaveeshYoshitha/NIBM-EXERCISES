package com.simpleCrudApplication_database.SimpleCrudApplicationDatabase.controller;

import com.simpleCrudApplication_database.SimpleCrudApplicationDatabase.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import com.simpleCrudApplication_database.SimpleCrudApplicationDatabase.services.StudentService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController
{
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudent();
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student)
    {
        return studentService.addStudent(student);
    }

    @DeleteMapping
    public void deleteStudent(@RequestParam Long id)
    {
        studentService.deleteStudent(id);
    }



}