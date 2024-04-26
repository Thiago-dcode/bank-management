package factory;

import exceptions.DuplicateEntityException;
import org.junit.jupiter.api.Test;
import org.mybank.User;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void createUser() throws DuplicateEntityException {
        assertInstanceOf(User.class, UserFactory.createUser());
    }

    @Test
    void createManyUsers() throws DuplicateEntityException {

        User[] users = UserFactory.createManyUsers(10);
        for (User user : users) {
            assertInstanceOf(User.class, user);
        }
    }
}