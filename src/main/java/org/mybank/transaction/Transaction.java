package org.mybank.transaction;

import org.mybank.Account;
import org.mybank.User;
import utils.DateUtils;
import utils.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public abstract class Transaction {
    private static int total = 0;
    private int id;
    private String date;
    private Account account;
    private String type;

    public Transaction( Account account,String type) {
        this.date = "";
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
        return type.toUpperCase()+ ": " + this.date + " [id=" + id ;
    }
    public void write(String type,String txt){
        this.date = DateUtils.getCurrentDate("dd/MM/yy HH:mm:ss");
        FileUtils.write("logs/transaction/ " + type,DateUtils.getCurrentDate("dd-MM-yyyy") +".txt",txt);
    }
    public void write(String txt){
       this.date = DateUtils.getCurrentDate("dd/MM/yy HH:mm:ss");
        FileUtils.write("logs/transaction/ " + type,DateUtils.getCurrentDate("dd-MM-yyyy") +".txt",txt);
    }
    abstract boolean execute();
    abstract void save();
}
