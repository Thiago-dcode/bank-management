package org.mybank.transaction;

import org.mybank.Account;
import org.mybank.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DepositTransaction  extends Transaction{
    double depositAmount;


    public DepositTransaction(User user, Account account, double depositAmount) {
        super(user, account,"deposit");
        this.depositAmount = depositAmount;
    }
    public boolean execute(){
        try{
            getAccount().deposit(depositAmount);
            write(toString());
            save();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }


    }

    public void save(){

    }

    public String toString(){

        return super.toString() + " amount: " + depositAmount + "]\n";

    }
}
