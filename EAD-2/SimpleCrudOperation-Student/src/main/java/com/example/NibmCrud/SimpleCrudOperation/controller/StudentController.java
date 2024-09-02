package com.example.NibmCrud.SimpleCrudOperation.controller;

import com.example.NibmCrud.SimpleCrudOperation.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>();
    private int currentId = 1;

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student loginStudent) {
        for (Student student : students) {
            if (student.getUser().equals(loginStudent.getUser()) && student.getPassword().equals(loginStudent.getPassword())) {
                return ResponseEntity.ok("Login Successful");
            }
        }
        return new ResponseEntity<>("Login Failed", HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<String> deleteStudent(@PathVariable String user) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUser().equals(user)) {
                students.remove(i);
                return ResponseEntity.ok("Student deleted successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
    }
}
