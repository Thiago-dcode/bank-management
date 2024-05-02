package gui.components.panels;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import exceptions.UnauthorizedException;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WithdrawPanel extends TransactionPanel {

    public WithdrawPanel(int accountId, Bank bank) {
        super(accountId,bank,"withdraw");

    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (textField.getText().isEmpty())return;
        try{
          super.bank.withdraw(super.accountId,Double.parseDouble(textField.getText()));
          Router.get("account",accountId +"");
        }
        catch(InvalidAmountException | InsufficientFundsException e){
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
