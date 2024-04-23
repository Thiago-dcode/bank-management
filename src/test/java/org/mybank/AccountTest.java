package org.mybank;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    User thiago = new User("thiago",30);
    Account account1 = new Account("Default",thiago, 2000);


    @Test
    void getBalance() {
        assertEquals(2000, account1.getBalance());
    }
    @Test
    void deposit(){
        assertEquals(4000, account1.deposit(2000).getBalance());

        try{
            account1.deposit(-2000);
        }
        catch (Exception e){
            assertInstanceOf(InvalidAmountException.class, e);
        }finally {
            assertEquals(4000, account1.getBalance());
        }
    }
    @Test
    void transfer(){
        Account toTransfer = new Account("Default",thiago, 2000);
        Account toTransfer1 = new Account("Default",thiago, 0);
        try{
            account1.transfer(toTransfer,5000);

        }catch(Exception e){
            assertInstanceOf(InsufficientFundsException.class, e);
        }finally {
            assertEquals(2000, toTransfer.getBalance());
            assertEquals(1500, account1.transfer(toTransfer,500).getBalance());
            assertEquals(2500, toTransfer.getBalance());
            assertEquals(0, toTransfer1.getBalance());
            assertEquals(1000, toTransfer.transfer(toTransfer1,1500).getBalance());
            assertEquals(1500, toTransfer1.getBalance());
        }

    }

    @Test
    void withdraw() {
        assertEquals(1000, account1.withdraw(1000).getBalance());
        try{
            account1.withdraw(5000);

        }catch(Exception e){
            assertInstanceOf(InsufficientFundsException.class, e);
        }finally {
            assertEquals(1000, account1.getBalance());
        }
        try{
            account1.withdraw(0);

        }catch (Exception e){
            assertInstanceOf(InvalidAmountException.class, e);
        }finally {
            assertEquals(1000, account1.getBalance());
        }
        try{
            account1.withdraw(-2000);

        }catch (Exception e){
            assertInstanceOf(InvalidAmountException.class, e);
        }finally {
            assertEquals(1000, account1.getBalance());
        }
    }
}