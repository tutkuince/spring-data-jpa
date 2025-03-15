package com.incetutku.persistinganentity;

import com.incetutku.persistinganentity.entity.Student;
import com.incetutku.persistinganentity.repository.MyCustomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersistingAnEntityApplicationTests {

    @Autowired
    private MyCustomRepository myCustomRepository;

    @Test
    void testCrud() {
        // Create
        Student student = new Student("Tutku Ince", "2025AN20123");
        Student returnedStudent = myCustomRepository.save(student);
        assertNotNull(returnedStudent.getId());

        // Update
        returnedStudent.setName("Utku Ince");
        Student updatedStudent = myCustomRepository.save(returnedStudent);
        assertEquals("Utku Ince", updatedStudent.getName());

        // Read
        Student foundStudent = myCustomRepository.findById(1L).orElseThrow(RuntimeException::new);
        assertEquals(1L, foundStudent.getId());

        // Delete
        myCustomRepository.delete(foundStudent);
        assertFalse(myCustomRepository.existsById(1L));
    }

}
