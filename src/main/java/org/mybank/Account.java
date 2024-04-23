package org.mybank;

import java.util.LinkedList;

public class Account {
    static int total = 0;
    private int id;
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        total++;
        this.id = total;
    }

    public double getBalance() {
        return balance;
    }

    public double withdraw(double amount) {
        this.balance -= amount;
        return amount;
    }
}
