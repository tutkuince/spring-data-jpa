package com.incetutku.persistinganentity;

import com.incetutku.persistinganentity.entity.Student;
import com.incetutku.persistinganentity.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersistingAnEntityApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testCrud() {
        // Create
        Student student = new Student("Tutku Ince", "2025AN20123");
        Student returnedStudent = studentRepository.save(student);
        assertNotNull(returnedStudent.getId());

        // Update
        returnedStudent.setName("Utku Ince");
        Student updatedStudent = studentRepository.save(returnedStudent);
        assertEquals("Utku Ince", updatedStudent.getName());

        // Read
        Student foundStudent = studentRepository.findById(1L).orElseThrow(RuntimeException::new);
        assertEquals(1L, foundStudent.getId());

        // Delete
        studentRepository.delete(foundStudent);
        assertFalse(studentRepository.existsById(1L));
    }

}
