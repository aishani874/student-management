package com.lab.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/students")
public class StudentApplication {

    private final List<String> students = new ArrayList<>(List.of("Alice", "Bob"));

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @GetMapping
    public List<String> getStudents() {
        return students;
    }

    @PostMapping
    public String addStudent(@RequestBody String name) {
        students.add(name);
        return "Student added: " + name;
    }
}