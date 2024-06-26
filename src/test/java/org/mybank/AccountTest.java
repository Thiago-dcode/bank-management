package org.mybank;

import exceptions.DuplicateEntityException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    User manolo ;
    Account account1 ;

    AccountTest() throws DuplicateEntityException {

    }
    @BeforeEach
    public void setUp() throws DuplicateEntityException {

           manolo =User.userExists("manolo")? User.getUser("manolo"): new User("manolo",30);
           account1 = new Account("Default",manolo, 2000);

    }


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
        Account toTransfer = new Account("Default",manolo, 2000);
        Account toTransfer1 = new Account("Default",manolo, 0);
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