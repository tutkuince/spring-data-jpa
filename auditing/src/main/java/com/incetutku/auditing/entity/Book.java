package com.incetutku.auditing.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners({AuditingEntityListener.class})    // Step 2: AuditingEntityListener -> uses @PrePersist and @PreUpdate callbacks
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    @CreatedDate        // Step 1
    private LocalDateTime createdDate;

    @LastModifiedDate   // Step 1
    private LocalDateTime lastModifiedDate;
}
