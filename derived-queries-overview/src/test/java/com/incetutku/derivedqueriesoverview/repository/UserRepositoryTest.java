package com.incetutku.derivedqueriesoverview.repository;

import com.incetutku.derivedqueriesoverview.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        User user1 = new User("tutku", LocalDate.of(2021, Month.AUGUST, 4), "tutku@mail.com", 1, true);
        User user2 = new User("utku", LocalDate.of(2020, Month.AUGUST, 4), "utku@mail.com", 2, true);
        User user3 = new User("ciko", LocalDate.of(2024, Month.AUGUST, 4), "ciko@mail.com", 3, true);
        User user4 = new User("gizo", LocalDate.of(2025, Month.AUGUST, 4), "gizo@mail.com", 3, true);
        User user5 = new User("eko", LocalDate.of(2020, Month.AUGUST, 4), "eko@mail.com", 1, true);
        User user6 = new User("serto", LocalDate.of(2025, Month.AUGUST, 4), "sertaga45@mail.com", 1, true);
        User user7 = new User("ibo", LocalDate.of(2025, Month.AUGUST, 4), "ibo@mail.com", 3, true);
        User user8 = new User("nailo", LocalDate.of(2025, Month.AUGUST, 4), "nailo@mail.com", 3, true);
        User user9 = new User("emino", LocalDate.of(2021, Month.AUGUST, 4), "emino@mail.com", 1, true);
        User user10 = new User("seherimou", LocalDate.of(2025, Month.AUGUST, 4), "seherimou@mail.com", 3, true);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
    }

    @Test
    void testFindAllPage1Of3Users() {
        Page<User> page1Of3User = userRepository.findAll(PageRequest.of(0, 3));
        List<User> users = page1Of3User.getContent();
        assertEquals(3, users.size());
        assertEquals("ciko", users.get(2).getUsername());
    }

    @Test
    void testFindAllPage2Of3Users() {
        Page<User> page2Of3User = userRepository.findAll(PageRequest.of(1, 3));
        List<User> users = page2Of3User.getContent();
        assertEquals(3, users.size());
        assertEquals("serto", users.get(2).getUsername());
    }
}