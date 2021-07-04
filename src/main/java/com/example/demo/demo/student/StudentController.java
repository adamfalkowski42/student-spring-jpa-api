package com.example.demo.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/student")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="/student/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

   @PutMapping(path="/student/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required=false) String name,
                              @RequestParam(required=false) String email){
        studentService.updateStudent(studentId,name,email);
   }

    @PutMapping(path="/student/")
    public void updateStudentBody(@RequestBody Student student){
        studentService.updateStudentBody(student);
    }

}
