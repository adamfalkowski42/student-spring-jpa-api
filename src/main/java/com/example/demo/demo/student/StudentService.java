package com.example.demo.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        } else {
            studentRepository.deleteById(studentId);
        }

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                ()-> new IllegalStateException("student with id " + studentId + " does not exist"));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null && email.length() >0 && !Objects.equals(student.getEmail(),email)){
            student.setEmail(email);
        }


    }
    @Transactional
    public void updateStudentBody(Student studentBody) {

        Student student = studentRepository.findById(studentBody.getId()).orElseThrow(
                ()-> new IllegalStateException("student with id " + studentBody.getId() + " does not exist"));
        if(studentBody.getName() != null && studentBody.getName().length() > 0 && !Objects.equals(student.getName(),studentBody.getName())){
            student.setName(studentBody.getName());
        }

        if(studentBody.getEmail() != null && studentBody.getEmail().length() >0 && !Objects.equals(student.getEmail(),studentBody.getEmail())){
            student.setEmail(studentBody.getEmail());
        }
    }
}
