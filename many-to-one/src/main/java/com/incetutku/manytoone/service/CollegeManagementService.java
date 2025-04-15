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
    public void persistingAGuideAlonWithItsAssociatedStudents() {
        Guide guide1 = new Guide("2000MO10789", "Mike Lawson", 1000);
        Guide guide2 = new Guide("2000IM10901", "Ian Lamb", 2000);

        Student student1 = new Student("2014JT50123", "John Smith", guide1);
        Student student2 = new Student("2014AL50456", "Amy Gill", guide2);

        guide1.getStudents().add(student1);
        guide1.getStudents().add(student2);

        guideRepository.save(guide1);
        guideRepository.save(guide2);
    }

    @Transactional(readOnly = true)
    public void findingAGuideByItsId() {
        Guide guide = guideRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(guide);
    }

    @Transactional(readOnly = true)
    public void updatingAStudent() {
        Guide guide = guideRepository.findById(2L).orElseThrow(RuntimeException::new);
        Student student = studentRepository.findById(2L).orElseThrow(RuntimeException::new);

        student.setGuide(guide);
    }

    @Transactional
    public void persistingAStudentAlongWithItsAssociatedGuide() {
        Guide guide = new Guide("2000MO10789", "Mike Lawson", 1000);
        Student student = new Student("2014JT50123", "John Smith", guide);

        // guideRepository.save(guide); // we do not need to save guide because it will be saved because of CascadeType.PERSIST
        studentRepository.save(student);
    }

    @Transactional
    public void persistingAStudentAlongWithItsAssociatedGuideFetchTypeMerge() {
        Student student = studentRepository.findById(1L).orElseThrow(RuntimeException::new);
        Guide guide = student.getGuide();

        student.setName("John Jr. Smith");
        guide.setSalary(2000);

        studentRepository.save(student);
    }

    @Transactional
    public void findingAStudentByItsId() {
        Student student = studentRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(student);
        System.out.println(student.getGuide().getStaffId());
    }
}
