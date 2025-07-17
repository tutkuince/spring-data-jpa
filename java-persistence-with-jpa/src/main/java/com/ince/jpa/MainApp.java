package com.ince.jpa;

import com.ince.jpa.config.JpaConfig;
import com.ince.jpa.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
        Map<String, Object> props = JpaConfig.getProps();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(props)
                .build();

        MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Employee.class);

        SessionFactory sessionFactory = sources.buildMetadata().buildSessionFactory();

        EntityManagerFactory entityManagerFactory = sessionFactory.unwrap(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Employee employee = new Employee();
        employee.setName("Tutku");
        employee.setSurname("Ince");
        employee.setYearsExperience(3);

        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
