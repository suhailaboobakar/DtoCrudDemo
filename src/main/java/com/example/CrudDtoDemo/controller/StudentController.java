package com.example.CrudDtoDemo.controller;

import com.example.CrudDtoDemo.entity.Student;
import com.example.CrudDtoDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student studentResp = studentService.createStudent(student);

        if (studentResp == null) {
            return null;
        }

        return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(@RequestParam Long id){
        Student studentResp = studentService.getStudentById(id);

        if (studentResp == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(studentResp);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentResp = studentService.getAllStudent();

        if (studentResp == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(studentResp);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }


    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id , @RequestBody Student req){
        Student studentResp = studentService.updateStudent(id , req);

        if (studentResp == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(studentResp);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentResp);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted) {
            return ResponseEntity
                    .notFound().build();
        }

        return ResponseEntity.ok("Record Deleted");
    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record Deleted");
    }
}
