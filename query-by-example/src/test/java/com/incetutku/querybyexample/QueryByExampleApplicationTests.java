package com.incetutku.querybyexample;

import com.incetutku.querybyexample.entity.User;
import com.incetutku.querybyexample.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QueryByExampleApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        User u1 = new User("gillamy01", "Amy", "Gill", "amy@mail.com", 1);
        User u2 = new User("smithjohn02", "John", "Smith", "john@mail.com", 2);
        User u3 = new User("lawsonmike03", "Mike", "Lawson", "mike@mail.com", 3);
        User u4 = new User("lambian03", "Ian", "Lamb", "ian@mail.com", 3);
        User u5 = new User("bailektora02", "Tora", "Bailek", "tora@mail.com", 2);
        User u6 = new User("smithsadie01", "Sadie", "Smith", "sadie@mail.com", 1);
        User u7 = new User("ambrizsharon01", "Sharon", "Ambriz", "sharon@mail.com", 1);
        User u8 = new User("singhrahul02", "Rahul", "Singh", "rahul@mail.com", 2);
        User u9 = new User("smithjoe01", "Joe", "Smith", "joe@mail.com", 2);
        User u10 = new User("johnsnonleo03", "Leo", "Johnson", "leo@mail.com", 3);
        User u11 = new User("leebrett04", "Brett", "Lee", "brett@mail.com", 4);

        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
        userRepository.save(u5);
        userRepository.save(u6);
        userRepository.save(u7);
        userRepository.save(u8);
        userRepository.save(u9);
        userRepository.save(u10);
        userRepository.save(u11);
    }

    @Test
    void testQueryByExample1() {
        User user = new User();
        user.setLastname("Smith");
        user.setLevel(2);

        Example<User> example = Example.of(user);
        List<User> matchingUsers = userRepository.findAll(example);
        System.out.println(matchingUsers);
    }

    @Test
    void testQueryByExample2() {
        User user = new User();
        user.setLastname("Smith");
        // user.setLevel(2); -> by default 0, because it is primitive type
        // default data attributes using primitive types are always included in probe entity instance.
        // Even if you do not provide any value for them, they'll be included with their default values.
        // Only the non-primitive data attributes are ignored if you do not provide any value for them.

        Example<User> example = Example.of(user);
        List<User> matchingUsers = userRepository.findAll(example);
        System.out.println(matchingUsers.size()); // so size is 0
        assertEquals(0, matchingUsers.size());
    }

    @Test
    void testQueryByExample3() {
        User user = new User();
        user.setLastname("Smith");
        // user.setLevel(2); -> by default 0, because it is primitive type

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("level");
        // ExampleMatcher -> carries details on how to match particular properties

        Example<User> example = Example.of(user, matcher);
        List<User> matchingUsers = userRepository.findAll(example);
        System.out.println(matchingUsers.size());   // 3
        assertEquals(3, matchingUsers.size());
    }

    @Test
    void testQueryByExample4() {
        User user = new User();
        user.setFirstname("s");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();

        Example<User> example = Example.of(user, matcher);
        List<User> matchingUsers = userRepository.findAll(example);
        System.out.println(matchingUsers.size());   // 2
        assertEquals(2, matchingUsers.size());
    }

    @Test
    void testQueryByExample5() {
        User user = new User();
        user.setFirstname("s");
        user.setEmail("@mail.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level")
                .withMatcher("firstname", ExampleMatcher.GenericPropertyMatcher::startsWith)
                .withMatcher("email", ExampleMatcher.GenericPropertyMatcher::endsWith)
                .withIgnoreCase();

        Example<User> example = Example.of(user, matcher);
        List<User> matchingUsers = userRepository.findAll(example);
        System.out.println(matchingUsers.size());   // 2
        assertEquals(2, matchingUsers.size());
    }

}
