package com.incetutku.manytoone.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staff_id", nullable = false)
    private String staffId;
    private String name;
    private Integer salary;
    @OneToMany(mappedBy = "guide", cascade = {CascadeType.PERSIST}, orphanRemoval = true)   // By default, @OneToMany associations are fetched LAZYly.
    private Set<Student> students = new HashSet<>();                                        // delete associated all students from the db with orphanRemoval

    public Guide() {
    }

    public Guide(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    // Helper method
    public void addStudent(Student student) {
        this.students.add(student);
        student.setGuide(this);
    }

    // Helper method
    public void removeStudent(Student student) {
        this.students.remove(student);
        student.setGuide(null);
    }

    // Helper method
    public void removeStudents() {
        Iterator<Student> iterator = this.students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            student.setGuide(null);
            iterator.remove();
        }
    }

    @Override
    public String toString() {
        return "Guide{" +
                "id=" + id +
                ", staffId='" + staffId + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
