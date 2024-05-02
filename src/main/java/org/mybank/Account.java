package org.mybank;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import org.mybank.transaction.Transaction;
import utils.MoneyUtil;

import java.util.LinkedList;

public class Account {
    static int total = 0;
    private int id;
    private  User user;
    private String name;
    private double balance;
    private final LinkedList<Transaction> transactions;

    public Account(String name,User user, double balance) {
        this.name = name;
        this.user = user;
        this.balance = balance;
        total++;
        this.id = total;
        transactions = new LinkedList<>();
        Bank.addAccount(user.getName(),this);
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
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Account transfer(Account to, double amount) throws InsufficientFundsException , InvalidAmountException {
        throwInvalidAmountException(amount);
        throwInsufficientFundsException(amount);
        this.balance -= amount;
        setBalance(MoneyUtil.round(this.balance,4));
        to.deposit(amount);

        return this;
    }
    public Account deposit(double amount) throws InsufficientFundsException {
       throwInvalidAmountException(amount);
        this.balance += amount;
        setBalance(MoneyUtil.round(this.balance,4));
        return this;
    }
    public Account withdraw(double amount) {
        throwInvalidAmountException(amount);
        throwInsufficientFundsException(amount);
        this.balance -= amount;
        setBalance(MoneyUtil.round(this.balance,4));
        return this;
    }
    private void throwInvalidAmountException(double amount) throws InvalidAmountException {
        if(amount <= 0 || amount > 10000){
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
    public String toString(Boolean showBalance){

        return "Account ID: " + id + " Name: " + name + (showBalance?" Balance: " + balance:"");
    }
    public String getName(){
        return name;
    }

}
