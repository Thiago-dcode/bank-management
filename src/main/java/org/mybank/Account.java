package org.mybank;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

import java.util.LinkedList;

public class Account {
    static int total = 0;
    private int id;
    private  User user;
    private String name;
    private double balance;

    public Account(String name,User user, double balance) {
        this.name = name;
        this.user = user;
        this.balance = balance;
        total++;
        this.id = total;
    }

    public double getBalance() {

        return balance;
    }
    private void throwInvalidAmountException(double amount) throws InvalidAmountException {
        if(amount <= 0){
            throw new InvalidAmountException();
        }
    }
    public Account deposit(double amount) {
       throwInvalidAmountException(amount);
        this.balance += amount;
        return this;
    }
    public Account withdraw(double amount) {
        throwInvalidAmountException(amount);
        if(amount > balance) {
            throw new InsufficientFundsException();
        }
        this.balance -= amount;
        return this;
    }

}
