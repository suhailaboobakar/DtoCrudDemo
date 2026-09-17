package com.example.CrudDtoDemo.service;

import com.example.CrudDtoDemo.dto.CreateStudentRequestDto;
import com.example.CrudDtoDemo.dto.CreateStudentResponseDto;
import com.example.CrudDtoDemo.dto.UpdateStudentRequestDto;
import com.example.CrudDtoDemo.dto.UpdateStudentResponseDto;
import com.example.CrudDtoDemo.entity.Student;
import com.example.CrudDtoDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto){
        Student student = mapToEntity(studentRequestDto);

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
//        student.setDeleted(false);
        Student studentResp = studentRepository.save(student);

        return mapToDto(studentResp);
    }


    public CreateStudentResponseDto getStudentById(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResp.isPresent()) {
            return mapToDto(studentResp.get());
        }
        return null;
    }

    public List<CreateStudentResponseDto> getAllStudent() {
        List<Student> studentResp = studentRepository.findByDeletedIsFalse();

        if (studentResp.isEmpty()) {
            return null;
        }

        return studentResp.stream()
                .map(this::mapToDto)
                .toList();
    }

    public UpdateStudentResponseDto updateStudent(Long id , UpdateStudentRequestDto studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToUpdate = existingStudent.get();

        studentToUpdate.setName(studentReq.getName());
        studentToUpdate.setAge(studentReq.getAge());
        studentToUpdate.setRollNo(studentReq.getRollNo());
        studentToUpdate.setSubject(studentReq.getSubject());
        studentToUpdate.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent =  studentRepository.save(studentToUpdate);
        return mapToUpdateDto(updatedStudent);
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

    private Student mapToEntity(CreateStudentRequestDto studentRequestDto){
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setEmail(studentRequestDto.getEmail());
        student.setRollNo(studentRequestDto.getRollNo());
        student.setSubject(studentRequestDto.getSubject());
        student.setDeleted(false);
        return student;
    }

    private CreateStudentResponseDto mapToDto(Student student) {
        CreateStudentResponseDto studentResponseDto = new CreateStudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setRollNo(student.getRollNo());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setMessage("Student saved successfully");
        studentResponseDto.setCreatedAt(student.getCreatedAt());
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());
        return studentResponseDto;
    }

    private UpdateStudentResponseDto mapToUpdateDto(Student updatedStudent) {
        UpdateStudentResponseDto studentResponseDto = new UpdateStudentResponseDto();
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setAge(updatedStudent.getAge());
        studentResponseDto.setRollNo(updatedStudent.getRollNo());
        studentResponseDto.setSubject(updatedStudent.getSubject());
        studentResponseDto.setMessage("Student updated successfully");
        studentResponseDto.setUpdatedAt(updatedStudent.getUpdatedAt());
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setEmail(updatedStudent.getEmail());
        return studentResponseDto;
    }
}
