package org.mybank.transaction;

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
    void setUp() {
        user = new User("thiago", 30);
        account = new Account("default",user,2000);
        depositTransaction = new DepositTransaction(user,account,2000);
    }

    @Test
    void depositTransaction() {

        assertTrue( depositTransaction.execute());
        assertEquals(4000,account.getBalance());


    }
    @Test
    void depositTransactionOnInvalidAmount() {

        depositTransaction = new DepositTransaction(user,account,-1000);
        assertFalse( depositTransaction.execute());
        assertEquals(2000,account.getBalance());


    }

}