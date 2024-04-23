package org.mybank;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UserTest {
    User user =  new User(1,"Thiago", 30);

    @Test
    void getId() {
        assertEquals(1,user.getId());
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