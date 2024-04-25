package org.mybank;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import org.mybank.transaction.Transaction;

import java.util.LinkedList;

public class Account {
    static int total = 0;
    private int id;
    private  User user;
    private String name;
    private double balance;
    private LinkedList<Transaction> transactions;

    public Account(String name,User user, double balance) {
        this.name = name;
        this.user = user;
        this.balance = balance;
        total++;
        this.id = total;
        this.transactions = new LinkedList<>();
    }
    public int getId() {
        return id;

    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }
    public double getBalance() {

        return balance;
    }
    public User getUser() {
        return user;
    }

    public Account transfer(Account to, double amount){
        throwInvalidAmountException(amount);
        throwInsufficientFundsException(amount);
        this.balance -= amount;
        to.deposit(amount);
        return this;
    }
    public Account deposit(double amount) throws InsufficientFundsException {
       throwInvalidAmountException(amount);
        this.balance += amount;
        return this;
    }
    public Account withdraw(double amount) {
        throwInvalidAmountException(amount);
        throwInsufficientFundsException(amount);
        this.balance -= amount;
        return this;
    }
    private void throwInvalidAmountException(double amount) throws InvalidAmountException {
        if(amount <= 0){
            throw new InvalidAmountException();
        }
    }
    private void throwInsufficientFundsException(double amount) throws InsufficientFundsException {
        if(amount > balance) {
            throw new InsufficientFundsException();
        }

    }
    @Override
    public String toString(){

        return "Account ID: " + id + " Name: " + name + " Balance: " + balance;
    }
    public String getName(){
        return name;
    }

}
