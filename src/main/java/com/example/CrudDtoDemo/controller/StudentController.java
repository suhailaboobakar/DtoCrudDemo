package com.example.CrudDtoDemo.controller;

import com.example.CrudDtoDemo.dto.CreateStudentRequestDto;
import com.example.CrudDtoDemo.dto.CreateStudentResponseDto;
import com.example.CrudDtoDemo.dto.UpdateStudentRequestDto;
import com.example.CrudDtoDemo.dto.UpdateStudentResponseDto;
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
    public ResponseEntity<CreateStudentResponseDto> create(@RequestBody CreateStudentRequestDto studentRequestDto) {
        CreateStudentResponseDto studentResp = studentService.createStudent(studentRequestDto);

        if (studentResp == null) {
            return null;
        }

        return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/get")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@RequestParam Long id){
        CreateStudentResponseDto studentResp = studentService.getStudentById(id);

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
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudent() {
        List<CreateStudentResponseDto> studentResp = studentService.getAllStudent();

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
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@RequestParam Long id , @RequestBody UpdateStudentRequestDto req){
        UpdateStudentResponseDto studentResp = studentService.updateStudent(id , req);

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
