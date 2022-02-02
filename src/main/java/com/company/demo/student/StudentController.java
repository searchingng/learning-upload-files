package com.company.demo.student;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{name}")
    public List<Student> getStudentsByName(@PathVariable String name){
        return studentService.getStudentsByName(name);
    }

    @PostMapping("/new")
    public void createNewStudent(@RequestBody Student student){
        studentService.createNewStudent(student);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletingStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "/update/{id}")
    public void updateStudent(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        studentService.updateStudent(id, name, email);
    };


}
