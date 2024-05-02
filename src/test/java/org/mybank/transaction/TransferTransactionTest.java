package org.mybank.transaction;

import exceptions.DuplicateEntityException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybank.Account;
import org.mybank.User;

import static org.junit.jupiter.api.Assertions.*;

class TransferTransactionTest {

    User user;
    Account account;
    User userTo;
    Account accountTo;
    TransferTransaction transferTransaction;
    @BeforeEach
    void setUp() throws DuplicateEntityException {
        user = User.userExists("thiago")? User.getUser("thiago"): new User("thiago",30);
        account = new Account("default",user,2000);
        userTo = User.userExists("manolo")? User.getUser("manolo"): new User("manolo",30);
        accountTo = new Account("savings",userTo,2000);
        transferTransaction = new TransferTransaction(account, 2000,accountTo);
    }

    @Test
    void transferTransaction() {

        assertTrue( transferTransaction.execute());
        assertEquals(0,account.getBalance());
        assertEquals(4000,accountTo.getBalance());
        assertTrue(account.getTransactions().contains(transferTransaction));

    }
    @Test
    void transferTransactionOnInvalidAmount() {

        transferTransaction = new TransferTransaction(account,3000,accountTo);
        assertThrows(InsufficientFundsException.class, ()->transferTransaction.execute());
        assertEquals(2000,account.getBalance());
        assertFalse(account.getTransactions().contains(transferTransaction));


    }
}