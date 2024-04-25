package org.mybank.transaction;

import org.mybank.Account;
import org.mybank.User;

public class WithdrawTransaction extends Transaction{
    double withdrawAmount;


    public WithdrawTransaction( Account account, double withdrawAmount) {
        super( account,"withdraw");
        this.withdrawAmount = withdrawAmount;
    }
    public boolean execute(){

            super.getAccount().withdraw(withdrawAmount);
            super.getAccount().addTransaction(this);
            write(toString());
            save();
            return true;

    }

    public void save(){

    }

    public String toString(){

        return super.toString() + " amount: " + withdrawAmount + "]\n";

    }
}
