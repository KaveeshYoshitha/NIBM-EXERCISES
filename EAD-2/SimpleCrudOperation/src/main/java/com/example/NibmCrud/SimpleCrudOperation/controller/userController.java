package com.example.NibmCrud.SimpleCrudOperation.controller;

import com.example.NibmCrud.SimpleCrudOperation.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class userController {
    private List<User> users = new ArrayList<>();
    private int currentId = 1;

    @GetMapping
    public List<User> getAllUsers(){
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(currentId++);
        users.add(user);
        return user;
    }

    @PostMapping("/login")
     ResponseEntity<String> login(@RequestBody User loginUser){
        for(User user : users){
            if(user.getName().equals(loginUser.getName()) && user.getPassword().equals(loginUser.getPassword())){
                return ResponseEntity.ok("Login Successful");
            }
        }
        return new ResponseEntity<>("Failed", HttpStatus.FORBIDDEN);
    }
}