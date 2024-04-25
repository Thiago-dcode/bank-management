package org.mybank.transaction;

import exceptions.InsufficientFundsException;
import org.mybank.Account;
import org.mybank.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DepositTransaction  extends Transaction{
    double depositAmount;


    public DepositTransaction(Account account, double depositAmount) {
        super(account,"deposit");
        this.depositAmount = depositAmount;
    }
    public boolean execute() throws InsufficientFundsException {

            getAccount().deposit(depositAmount);
            getAccount().addTransaction(this);
            write(toString());
            save();
            return true;


    }

    public void save(){

    }

    public String toString(){

        return super.toString() + " amount: " + depositAmount + "]\n";

    }
}
