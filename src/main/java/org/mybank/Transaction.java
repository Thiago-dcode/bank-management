package org.mybank;

public class Transaction {
    private int id;
    private User user;
    private Account account;
    private int amount;
    private String type;
    public Transaction(int id,User user, Account account, int amount, String type) {
        this.id = id;
        this.user = user;
        this.account = account;
        this.amount = amount;
        this.type = type;
    }
}
