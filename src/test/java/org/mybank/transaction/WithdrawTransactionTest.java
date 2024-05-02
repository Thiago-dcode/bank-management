package org.mybank.transaction;

import exceptions.DuplicateEntityException;
import exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybank.Account;
import org.mybank.User;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawTransactionTest {

    User user;
    Account account;
    WithdrawTransaction withdrawTransaction;
    @BeforeEach
    void setUp() throws DuplicateEntityException {

        user =User.userExists("Thiago")? User.getUser("Thiago"): new User("Thiago", 30);
        account = new Account("default",user,2000);
        withdrawTransaction = new WithdrawTransaction(account,2000);
    }

    @Test
    void withdrawTransaction() {

        assertTrue( withdrawTransaction.execute());
        assertEquals(0,account.getBalance());


    }
    @Test
    void withdrawTransactionOnInvalidAmount() {

        withdrawTransaction = new WithdrawTransaction(account,3000);
        assertThrows(InsufficientFundsException.class, ()->withdrawTransaction.execute());
        assertEquals(2000,account.getBalance());


    }
}