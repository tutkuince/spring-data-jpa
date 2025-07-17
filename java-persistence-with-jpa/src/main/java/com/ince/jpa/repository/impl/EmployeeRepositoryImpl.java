package com.ince.jpa.repository.impl;

import com.ince.jpa.entity.Employee;
import com.ince.jpa.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (Objects.isNull(employee.getId())) {
                entityManager.persist(employee);
            } else {
                employee = entityManager.merge(employee);
            }
            transaction.commit();
            return Optional.of(employee);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Employee employee = entityManager.find(Employee.class, id);
        return Optional.ofNullable(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(employee) ? employee : entityManager.merge(employee));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getEmployeesByExperience(Integer yearsExperience) {
        return entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.yearsExperience = :exp", Employee.class)
                .setParameter("exp", yearsExperience)
                .getResultList();

    }

    @Override
    public List<Employee> getEmployeesByExperienceNativeQuery(Integer yearsExperience) {
        return entityManager.createNamedQuery(
                        "SELECT * FROM employees WHERE years_experience = ?", Employee.class)
                .setParameter(1, yearsExperience)
                .getResultList();
    }
}
