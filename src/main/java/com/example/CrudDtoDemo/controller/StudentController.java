package com.example.CrudDtoDemo.controller;

import com.example.CrudDtoDemo.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController {

    public ResponseEntity<Student> create(@RequestBody Student student) {

    }
}
