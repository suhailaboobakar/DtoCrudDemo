package com.example.CrudDtoDemo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2 , max = 50 , message = "Name should be within 2 to 50 characters long")
    private String name;
    @Email(message = "Enter valid email.")
    @NotBlank(message = "Email is required")
    private String email;
    @Min(value = 18 , message = "Age must be at least 18")
    private int age;
    @NotBlank(message = "Subject is required")
    private String subject;
    @NotNull(message = "Roll number cannot be null or empty")
    @Positive(message = "Roll number must be a positive value")
    private int rollNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
}
