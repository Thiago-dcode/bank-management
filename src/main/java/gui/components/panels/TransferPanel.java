package gui.components.panels;

import exceptions.InvalidAmountException;
import exceptions.UnauthorizedException;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TransferPanel extends TransactionPanel {

    private final String usernameTo;
    private final int accountIdTo;

    public TransferPanel(int accountId, Bank bank, String usernameTo, int accountIdTo) {
        super(accountId,bank,"transfer");
        this.usernameTo = usernameTo;
        this.accountIdTo = accountIdTo;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (textField.getText().isEmpty())return;
        try{
            double amount = Double.parseDouble(super.textField.getText());
          super.bank.transfer(super.accountId,this.usernameTo,this.accountIdTo,amount);
          Router.get("account",accountId +"");
        }
        catch(InvalidAmountException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        catch (UnauthorizedException e){
            Router.get("login");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
