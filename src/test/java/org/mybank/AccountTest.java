package org.mybank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account = new Account("Default", 2000);

    @Test
    void getBalance() {
        assertEquals(2000, account.getBalance());
    }

    @Test
    void withdraw() {
        assertEquals(1000, account.withdraw(1000));

        try{
            account.withdraw(5000);
            assertEquals(2000, account.getBalance());


        }catch(Exception e){
            assertEquals(E, Exception instanceof );
        }
    }
}