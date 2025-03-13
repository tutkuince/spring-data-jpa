package com.incetutku.persistinganentity.client;

import com.incetutku.persistinganentity.entity.Student;
import com.incetutku.persistinganentity.repository.StudentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentClient implements ApplicationRunner {

    private final StudentRepository studentRepository;

    public StudentClient(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Student student = new Student("Tutku Ince", "2025AN50123");
        studentRepository.save(student);
    }
}
