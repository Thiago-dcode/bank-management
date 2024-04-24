package org.mybank.transaction;

import org.mybank.Account;
import org.mybank.User;
import utils.DateUtils;
import utils.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Transaction {
    private static int total = 0;
    private int id;
    private User user;
    private Account account;
    private String type;

    public Transaction(User user, Account account,String type) {
        this.user = user;
        this.account = account;
        this.type = type;
               total++;
        this.id = total;
    }

    public int getId() {
        return id;
    }
    public Account getAccount() {
        return account;
    }

    @Override
    public String toString(){
        return type+ "-transaction " + DateUtils.getCurrentDate("dd/mm/yy HH:mm:ss") + " [id=" + id  + ", user=" + user.getId() + ", account=" + account.getId() ;
    }

    public void write(String txt){
        FileUtils.write("logs/transaction/ " + type,DateUtils.getCurrentDate("dd-MM-yyyy") +".txt",txt);
    }
    abstract boolean execute();
    abstract void save();
}
