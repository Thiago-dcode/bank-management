package factory;

import com.github.javafaker.Faker;
import exceptions.DuplicateEntityException;
import org.mybank.User;

public class UserFactory {
   static Faker faker = new Faker();

    public static User createUser() throws DuplicateEntityException {
        String name = faker.name().username();
        int age = faker.number().numberBetween(18,90);
        return User.userExists(name)? User.getUser(name): new User(name,age);
    }
    public static User[] createManyUsers(int n) throws DuplicateEntityException {
        User[] users = new User[n];
        for (int i = 0; i < n; i++) {
            users[i] = createUser();
        }
        return users;
    }
}
