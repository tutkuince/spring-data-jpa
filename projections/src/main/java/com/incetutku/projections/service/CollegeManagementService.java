package com.incetutku.projections.service;

import com.incetutku.projections.entity.Guide;
import com.incetutku.projections.projection.GuideNameSalary;
import com.incetutku.projections.repository.GuideRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollegeManagementService {

    private final GuideRepository guideRepository;

    public CollegeManagementService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    public void populateData() {
        Guide g1 = new Guide("2000MO10178", "Mike Lawson", 1000);
        Guide g2 = new Guide("2008IM10901", "Ian Lamb", 4000);
        Guide g3 = new Guide("2012DO10777", "David Crow", 3000);
        Guide g4 = new Guide("2020HN10865", "Henry Smith", 2000);
        Guide g5 = new Guide("2021DO10499", "Diane Lynn", 3500);
        Guide g6 = new Guide("2017ES50489", "Eric Walsh", 2500);

        guideRepository.saveAll(List.of(g1, g2, g3, g4, g5, g6));
    }

    @Transactional(readOnly = true)
    public void displayNameAndSalaryOfFirst3GuidesBySalaryGreaterThan2000() {
        List<GuideNameSalary> proxies = guideRepository.findFirst3BySalaryGreaterThan(2000);

        proxies.forEach(guide -> System.out.println("Name: " + guide.getName() + "\t\t Salary: " + guide.getSalary()));
    }
}
