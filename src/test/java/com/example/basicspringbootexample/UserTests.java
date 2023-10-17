package com.example.basicspringbootexample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.basicspringbootexample.messaging.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTests {

    User user;

    @Test
    void displaysFullName() {
        user = new User("TestFirst", "TestLast");

        assertEquals("TestFirst TestLast", user.FullName());
    }
}
