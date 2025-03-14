package com.incetutku.persistinganentity.client;

import com.incetutku.persistinganentity.entity.Student;
import com.incetutku.persistinganentity.repository.StudentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentClient implements ApplicationRunner {

    private final StudentRepository studentRepository;

    public StudentClient(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Persisting a student
        Student student = new Student("Tutku Ince", "2025AN50123"); // transient state
        Student savedStudent = studentRepository.save(student);    // save=> persist() persistent state
        // after transaction commit complete, the entity manager will be closed.
        // once that happens, our student object and savedStudent will move from the persistent state to the detached state.
        // it's no longer being managed by our entity manager
        // Once the line 21 finishes its execution, this savedStudent variable and also this student variable they both would be
        // referencing to the same student objects in the detached state.

        // transient state -> persistent state(save) -> detached state (after save)

        // Updating a student
        savedStudent.setName("Utku Ince");
        Student updatedStudent = studentRepository.save(savedStudent);  // update=> merge()

        Optional<Student> optionalStudent = studentRepository.findById(1L);
        Student selectedStudent = optionalStudent.orElseThrow(RuntimeException::new);
        System.out.println(selectedStudent);
    }
}
