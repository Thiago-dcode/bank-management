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

public class LoginPanel extends javax.swing.JPanel implements ActionListener {
JLabel label;
JTextField textField;
JButton button;
JLabel errorLabel;
Bank bank;

    public LoginPanel(Bank bank) {
        this.bank = bank;

        style();
        renderComponents();
    }


    public void style(){

        super.setLayout(null);
        super.setBounds(0, 100, 100, 100);
       super.setBorder(BorderFactory.createLineBorder(Color.black,5));

    }

    public void renderComponents(){
        label = new JLabel("Login");
        label.setBounds(150, 50, 100, 20);
        this.textField= new JTextField(16);
        this.textField.setBounds(150, 75, 200, 20);
        this.textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                errorLabel.setText("");
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.button = new JButton("Submit");
        this.button.addActionListener(this);
        this.button.setBounds(250, 100, 100, 20);
        this.errorLabel = new JLabel();
        this.errorLabel.setBounds(150, 125, 200, 20);
       super.add(label);
       super.add(this.textField);
       super.add(this.button);
       super.add(this.errorLabel);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        try{
            bank.loginUser(textField.getText());
            //success
            Router.get("user");
        }
        catch(UserNotFoundException une){
            this.errorLabel.setText(une.getMessage());
        }
    }
}
