package factory;

import com.github.javafaker.Faker;
import exceptions.DuplicateEntityException;
import org.mybank.Account;
import org.mybank.User;

import java.util.LinkedList;

public class AccountFactory {
    static Faker faker = new Faker();

    public static Account createAccount() throws DuplicateEntityException {
        String name = faker.name().name();
       User user = UserFactory.createUser();
       int balance = faker.number().numberBetween(0, 5000);
        return new Account(name,user,balance);
    }
    public static Account createAccount(User user) throws DuplicateEntityException {
        String name = faker.name().name();
        int balance = faker.number().numberBetween(0, 5000);
        return new Account(name,user,balance);
    }

    public static LinkedList<Account> createManyAccounts(int n) throws DuplicateEntityException {
        LinkedList<Account> accounts = new LinkedList<Account>();
        for (int i = 0; i < n; i++) {
            accounts.add( createAccount());
        }
        return accounts;
    }
    public static LinkedList<Account> createManyAccounts(User user,int n) throws DuplicateEntityException {
        LinkedList<Account>  accounts = new LinkedList<Account>();
        for (int i = 0; i < n; i++) {
            accounts.add(createAccount(user));
        }
        return accounts;
    }

    public static LinkedList<Account> createManyAccountsByUsers(User[] users, int n) throws DuplicateEntityException {
        LinkedList<Account>  accounts = new LinkedList<Account>();
       for(User user : users) {
           accounts.addAll(createManyAccounts(user,n));
       }
       return accounts;
    }
//    public static Accounts[] createManyAccounts(int n) throws DuplicateEntityException {
//        User[] users = new User[n];
//
//        return users;
//    }
}
