package com.example.student_crm_project.service.impl;

import com.example.student_crm_project.dto.StudentDTO;
import com.example.student_crm_project.entity.Student;
import com.example.student_crm_project.exception.StudentNotFoundException;
import com.example.student_crm_project.repository.StudentRepository;
import com.example.student_crm_project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(int id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not found with id: "+id));
        return convertEntityToDTO(student);
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = convertDTOToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertEntityToDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(int id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Student not found with id: "+id));
        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setPhone(studentDTO.getPhone());

        Student updatedStudent = studentRepository.save(existingStudent);

        return convertEntityToDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Student not found with id:"+id));
        studentRepository.deleteById(id);
    }

    // Conversion methods
    private StudentDTO convertEntityToDTO(Student student) {
        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getPhone());
    }

    private Student convertDTOToEntity(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getEmail(), studentDTO.getPhone());
    }

}
