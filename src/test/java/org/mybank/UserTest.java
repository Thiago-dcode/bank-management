package org.mybank;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.DuplicateEntityException;
import factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class UserTest {

    User user ;


    @BeforeEach
    void setUp() throws Exception {

        user = User.userExists("Thiago")? User.getUser("Thiago"): new User("Thiago", 30);
    }
    @Test
    void shouldOnlyExistOneUserPerUsername(){

        assertThrows(DuplicateEntityException.class, () -> {
            new User("Thiago", 30);
        });
    }
    @Test
    void shouldReturnBooleanIfUserExists(){
        assertTrue(User.userExists("Thiago"));
        assertFalse(User.userExists("fdfdf"));

    }

    @Test
    void getId() throws DuplicateEntityException {
        User _user =  new User("Manolo", 30);
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
    @Test
    void getALL() throws DuplicateEntityException {
        User[] users = UserFactory.createManyUsers(10);
        assertEquals(11,User.getAll().size());
    }
}