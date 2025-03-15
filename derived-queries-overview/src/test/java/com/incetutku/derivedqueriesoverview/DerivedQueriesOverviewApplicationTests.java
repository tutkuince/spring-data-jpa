package com.incetutku.derivedqueriesoverview;

import com.incetutku.derivedqueriesoverview.entity.Student;
import com.incetutku.derivedqueriesoverview.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DerivedQueriesOverviewApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeAll
    void populateDB() {
        Student s1 = new Student("Alissa Simmons", "2022AN50123");
        Student s2 = new Student("Tora Bialek", "2007TE50698");
        Student s3 = new Student("Sharon Ambriz", "2025SI50213");
        Student s4 = new Student("Tianna Armentrout", "2022TU50471");
        Student s5 = new Student("Angelica Zapien", "2022AE50802");
        Student s6 = new Student("Aarti Evatt", "2022AT50385");

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
        studentRepository.save(s4);
        studentRepository.save(s5);
        studentRepository.save(s6);
    }

    @AfterAll
    void dePopulateDB() {
        studentRepository.deleteAll();
    }

    @Test
    void testFindByEnrollmentId() {
        Student student = studentRepository.findByEnrollmentId("2025SI50213");
        assertEquals("2025SI50213", student.getEnrollmentId());
        System.out.println(student);
    }

    @Test
    void testFindByEnrollmentIdStartingWithAndNameLike() {
        List<Student> students = studentRepository.findByEnrollmentIdStartingWithAndNameLike("2022", "A%");
        assertEquals(3, students.size());
        System.out.println(students);
    }

    @Test
    void testFindFirst2ByEnrollmentIdStartingWithAndNameLike() {
        List<Student> students = studentRepository.findFirst2ByEnrollmentIdStartingWithAndNameLike("2022", "A%");
        assertEquals(2, students.size());
        System.out.println(students);
    }
}
