package org.mybank;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class UserTest {
    User user =  new User("Thiago", 30);
    @Test
    void getId() {
        User _user =  new User("Thiago", 30);
        assertEquals(User.getTotal(),_user.getId());
    }

    @Test
    void getName() {
        assertEquals("Thiago",user.getName());
    }

    @Test
    void getAge() {
        assertEquals(30,user.getAge());
    }
}