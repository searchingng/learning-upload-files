package com.company.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByName(String name){
        return studentRepository.findByNameQuery(name);
    }

    public void createNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository
                .findByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Kalla, email uje bor");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw new IllegalStateException("Kalla, bunday id yo'o'o'q");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email){
        if (!studentRepository.existsById(id)){
            throw new IllegalStateException("Kalla, bunday id yo'o'o'q");
        }
        Student student = studentRepository.getById(id);

        if (Objects.equals(student.getName(), name)){
            throw new IllegalStateException("Avvalgi bilan bir xil bo'lmasin");
        }
        student.setName(name);
        if (Objects.equals(student.getEmail(), email)){
            throw new IllegalStateException("Boshqa email kiriting");
        }
        student.setEmail(email);
    }

    public Student update(Long id, Student student){
        student.setId(id);
        return student;
    }
}
