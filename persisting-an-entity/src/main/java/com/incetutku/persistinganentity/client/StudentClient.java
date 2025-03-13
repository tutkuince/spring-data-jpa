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
        // Persisting a student
        Student student = new Student("Tutku Ince", "2025AN50123");
        Student savedStudent = studentRepository.save(student);    // update=> merge() | save=> persist()

        // transient state -> persistent state(save) -> detached state (after save)

        // Updating a student
        savedStudent.setName("Utku Ince");
        Student updatedStudent = studentRepository.save(savedStudent);
    }
}
