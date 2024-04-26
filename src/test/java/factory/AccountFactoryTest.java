package factory;

import exceptions.DuplicateEntityException;
import org.junit.jupiter.api.Test;
import org.mybank.Account;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {

    @Test
    void createAccout() throws DuplicateEntityException {
        assertInstanceOf(Account.class, AccountFactory.createAccount());
        assertInstanceOf(Account.class, AccountFactory.createAccount(UserFactory.createUser()));
    }
    @Test
    void createManyAccounts() throws DuplicateEntityException {
        LinkedList<Account> accounts = AccountFactory.createManyAccounts(5);
        LinkedList<Account> accounts1 = AccountFactory.createManyAccounts(UserFactory.createUser(),5);
        for (int i = 0; i < accounts.size(); i++) {
            assertInstanceOf(Account.class, accounts.get(i));
            assertInstanceOf(Account.class, accounts1.get(i));
        }

    }
    @Test
    void createManyAccountsByUsers() throws DuplicateEntityException {
        LinkedList<Account> accounts = AccountFactory.createManyAccountsByUsers(UserFactory.createManyUsers(5),5);
        assertEquals(25, accounts.size());
    }
}