package com.example.CrudDtoDemo.service;

import com.example.CrudDtoDemo.entity.Student;
import com.example.CrudDtoDemo.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        studentReq.setDeleted(false);
        Student StudentResp = studentRepository.save(studentReq);
        return StudentResp;
    }


    public Student getStudentById(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResp.isPresent()) {
            return studentResp.get();
        }
        return null;
    }

    public List<Student> getAllStudent() {
        List<Student> studentResp = studentRepository.findByDeletedIsFalse();

        if (studentResp.isEmpty()) {
            return null;
        }

        return studentResp;
    }

    public Student updateStudent(Long id , Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToUpdate = existingStudent.get();

        studentToUpdate.setName(studentReq.getName());
        studentToUpdate.setAge(studentReq.getAge());
        studentToUpdate.setEmail(studentReq.getEmail());
        studentToUpdate.setRollNo(studentReq.getRollNo());
        studentToUpdate.setSubject(studentReq.getSubject());

        Student updatedStudent =  studentRepository.save(studentToUpdate);
        return updatedStudent;
    }

    public boolean deleteStudent(Long id) {
        boolean isUserExist = studentRepository.existsById(id);

        if (!isUserExist) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()) {
            return false;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);
        return true;
    }
}
