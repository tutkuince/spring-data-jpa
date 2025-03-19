package com.incetutku.auditing.repository;

import com.incetutku.auditing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
