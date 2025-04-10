package com.incetutku.projections.projection;

import org.springframework.beans.factory.annotation.Value;

public interface GuideNameSalary {

    String getName();
    String getSalary();

    // @Value("#{target.name} \t #{target.salary}")    // loading the data of all of its data attributes
    //String getInfo();                               // you should use it with @Query annotation in repository to avoid load unnecessary data

    @Value("#{target.staffId} \t #{target.name} \t #{target.salary}")
    String getInfo();
}
