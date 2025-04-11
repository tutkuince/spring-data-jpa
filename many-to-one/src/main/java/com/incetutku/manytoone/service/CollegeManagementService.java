package com.incetutku.manytoone.service;

import com.incetutku.manytoone.entity.Guide;
import com.incetutku.manytoone.entity.Student;
import com.incetutku.manytoone.repository.GuideRepository;
import com.incetutku.manytoone.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollegeManagementService {

    private final StudentRepository studentRepository;
    private final GuideRepository guideRepository;

    public CollegeManagementService(StudentRepository studentRepository, GuideRepository guideRepository) {
        this.studentRepository = studentRepository;
        this.guideRepository = guideRepository;
    }

    @Transactional
    public void persistingAStudentAlongWithItsAssociatedGuide() {
        Guide guide = new Guide("2000MO10789", "Mike Lawson", 1000);
        Student student = new Student("2014JT50123", "John Smith", guide);

        // guideRepository.save(guide); // we do not need to save guide because it will be saved because of CascadeType.PERSIST
        studentRepository.save(student);
    }

    @Transactional
    public void findingAStudentByItsId() {
        Student student = studentRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(student);
        System.out.println(student.getGuide().getStaffId());
    }
}
