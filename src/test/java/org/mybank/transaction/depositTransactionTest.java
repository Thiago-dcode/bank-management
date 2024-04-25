package org.mybank.transaction;

import exceptions.DuplicateEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mybank.Account;
import org.mybank.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepositTransactionTest {


    User user;
    Account account;
    DepositTransaction depositTransaction;
    @BeforeEach
    void setUp() throws DuplicateEntityException {
        user = new User("thiago", 30);
        account = new Account("default",user,2000);
        depositTransaction = new DepositTransaction(account,2000);
    }

    @Test
    void depositTransaction() {

        assertTrue( depositTransaction.execute());
        assertEquals(4000,account.getBalance());
        assertTrue(account.getTransactions().contains(depositTransaction));


    }
    @Test
    void depositTransactionOnInvalidAmount() {

        depositTransaction = new DepositTransaction(account,-1000);
        assertFalse( depositTransaction.execute());
        assertEquals(2000,account.getBalance());
        assertFalse(account.getTransactions().contains(depositTransaction));

    }

}