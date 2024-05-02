package gui.components.panels;
import exceptions.InvalidAmountException;
import exceptions.UnauthorizedException;
import org.mybank.Bank;
import router.Router;
import javax.swing.*;
import java.awt.event.ActionEvent;
public class DepositPanel  extends TransactionPanel {

    public DepositPanel(int accountId, Bank bank) {
        super(accountId,bank,"deposit");

    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (textField.getText().isEmpty())return;
        try{
            double amount = Double.parseDouble(super.textField.getText());

          super.bank.deposit(super.accountId,amount);
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
