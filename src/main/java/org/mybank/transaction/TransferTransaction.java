package org.mybank.transaction;

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
    public boolean execute(){
        try{
            getAccount().transfer(to,transferAmount);
            getAccount().addTransaction(this);
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

        return super.toString() + ", User transferred= " + to.getUser().getName() + ", Account transferred= " + to.getName()+", amount: " + transferAmount + "]\n";

    }
}
