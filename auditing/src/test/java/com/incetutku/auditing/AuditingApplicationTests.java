package com.incetutku.auditing;

import com.incetutku.auditing.entity.Book;
import com.incetutku.auditing.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AuditingApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testAuditing() {
        Book book = new Book();
        book.setIsbn("001-SDJ");
        book.setTitle("Core");

        Book savedBook = bookRepository.save(book);
        System.out.println("Saved Book: " + savedBook);

        book.setTitle("Core, 2nd Edition");

        Book updatedBook = bookRepository.save(book);
        System.out.println("Updated Book: " + updatedBook);

        assertNotNull(book.getCreatedDate());
        assertNotNull(book.getLastModifiedDate());
    }

    @Test
    void testAuditing2() {
        Book book = new Book();
        book.setIsbn("001-SDJ");
        book.setTitle("Core");

        Book savedBook = bookRepository.save(book);
        System.out.println("Saved Book: " + savedBook);

        book.setTitle("Core, 2nd Edition");

        Book updatedBook = bookRepository.save(book);
        System.out.println("Updated Book: " + updatedBook);

        assertNotNull(book.getCreatedDate());
        assertNotNull(book.getLastModifiedDate());
        assertNotNull(book.getCreatedBy());
        assertNotNull(book.getLastModifiedBy());
    }

}
