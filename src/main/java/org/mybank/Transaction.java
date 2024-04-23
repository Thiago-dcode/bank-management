package org.mybank;
public abstract class Transaction {
    private static int total = 0;
    private int id;
    private User user;
    private Account account;
    private int amount;

    public Transaction(User user, Account account, int amount) {
        this.user = user;
        this.account = account;
        this.amount = amount;
        total++;
        this.id = total;

    }
    @Override
    public String toString(){

        return "Transaction [id=" + id + ", user=" + user + ", account=" + account.getName() + ", amount=" + amount;
    }
}
