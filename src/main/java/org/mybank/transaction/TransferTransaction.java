package org.mybank.transaction;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import org.mybank.Account;
import org.mybank.User;

public class TransferTransaction extends Transaction{
    double transferAmount;
    Account to;


    public TransferTransaction(Account account, double transferAmount, Account to) {
        super( account,"transfer");
        this.to = to;
        this.transferAmount = transferAmount;
    }
    public boolean execute() throws InsufficientFundsException, InvalidAmountException {
            getAccount().transfer(to,transferAmount);
            getAccount().addTransaction(this);
            to.addTransaction(this);
            write(toString());
            save();
            return true;

    }

    public void save(){

    }
//    public String toStringTo(){
//
//        return super.toString() + ", From= " + getAccount().getUser().getName() + ", amount: " + transferAmount + "]\n";
//
//    }

    public String toString(){

        return super.toString() + ", From= " + getAccount().getUser().getName() + ", To= " + to.getUser().getName() + ", amount: " + transferAmount + "]\n";

    }
}
