package com.incetutku.persistinganentity.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "enrollment_id", nullable = false, unique = true)
    private String enrollmentId;

    public Student() {
    }

    public Student(String name, String enrollmentId) {
        this.name = name;
        this.enrollmentId = enrollmentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enrollmentId='" + enrollmentId + '\'' +
                '}';
    }
}
