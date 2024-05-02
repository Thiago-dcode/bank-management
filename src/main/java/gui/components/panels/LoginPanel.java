package gui.components.panels;

import exceptions.UserNotFoundException;
import gui.Frame;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;

public class LoginPanel extends AuthPanel{


    public LoginPanel(Bank bank) {
      super(bank,"Login");
        renderComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        try{
            bank.loginUser(textField.inputField.getText());
            //success
            Router.get("user");
        }
        catch(UserNotFoundException une){
            this.errorLabel.setText(une.getMessage());
        }
    }
}
