package com.example.CrudDtoDemo.dto;

import jakarta.validation.constraints.*;

public class UpdateStudentRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2 , max = 50 , message = "Name should be within 2 to 50 characters long")
    private String name;
    @NotNull(message = "Roll number cannot be null or empty")
    @Positive(message = "Roll number must be a positive value")
    private int rollNo;
    @NotBlank(message = "Subject is required")
    private String subject;
    @Min(value = 18 , message = "Age must be at least 18")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
