/*
package com.company.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student s = new Student(
                    "Sirojiddin",
                    "ssirojiddin2004@gmail.com",
                    LocalDate.of(2004, 05, 01)
            );

            Student ss = new Student(
                    "Sohibjon",
                    "sohibjon2000@gmail.com",
                    LocalDate.of(2000, 9, 22)
            );

            studentRepository.saveAll(
                    List.of(s, ss)
            );
        };
    }

}
*/
