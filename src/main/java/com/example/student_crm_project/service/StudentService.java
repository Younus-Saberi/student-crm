package com.example.student_crm_project.service;

import com.example.student_crm_project.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(int id);

    StudentDTO saveStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(int id, StudentDTO studentDTO);

    void deleteStudent(int id);
}
