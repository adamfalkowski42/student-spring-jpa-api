package com.example.demo.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student adam = new Student(1L, "Adam", LocalDate.of(2000, Month.JANUARY, 1), "test@gmail.com");
            Student bob = new Student("Bob", LocalDate.of(2000, Month.JANUARY, 1), "test2@gmail.com");
            studentRepository.saveAll(List.of(adam,bob));
        };
    }

}
