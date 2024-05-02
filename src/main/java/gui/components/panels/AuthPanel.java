package gui.components.panels;

import exceptions.UserNotFoundException;
import gui.components.labels.TextField;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class AuthPanel extends JPanel implements ActionListener {
    private final String authType;
    JButton button;
    JLabel errorLabel;
    Bank bank;
    public TextField textField;
    public AuthPanel(Bank bank, String authType) {
        this.bank = bank;
        this.authType = authType;
        style();
    }

    public void style() {

        super.setLayout(null);
        super.setBounds(0, 100, 100, 100);
        super.setBorder(BorderFactory.createLineBorder(Color.black, 5));

    }
    public void renderTitle(){
        JLabel titleLabel = new JLabel( authType.toUpperCase());
        titleLabel.setBounds(225,5,200,50);
        super.add(titleLabel);
    }
    public void renderTextField(int y) {
        textField = new TextField(150,y,200,20,"username",new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                errorLabel.setText("");
            }
        });
        super.add(textField);
//        label = new JLabel("Username");
//        label.setBounds(150, y, 100, 20);
//        this.textField = new JTextField(16);
//        this.textField.setBounds(150, y+25, 200, 20);
//        this.textField.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                errorLabel.setText("");
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//        super.add(label);
//        super.add(this.textField);
    }
    public void renderTextField(){
        renderTextField(100);
    }

    public void renderSubmit(int y) {
        this.button = new JButton("Submit");
        this.button.addActionListener(this);
        this.button.setBounds(250, y, 100, 20);
        this.errorLabel = new JLabel();
        this.errorLabel.setBounds(150, y+25, 200, 20);
        super.add(this.button);
        super.add(this.errorLabel);
    }
    public void renderSubmit() {
        renderSubmit(150);
    }

    public void renderLink(int y) {
       JButton link = new JButton( authType.equalsIgnoreCase("register")?"Already have an account?":"Not have an account?") ;
        link.addActionListener((e) -> {
            System.out.println(authType);
            switch (authType.toLowerCase()) {
                case "login":
                    Router.get("register");
                    return;
                case "register":
                    Router.get("login");
            }
        });
        link.setBounds(150, y, 200, 20);
        super.add(link);

    }
    public void renderLink(){
        renderLink(300);
    }

    public void renderComponents() {
        renderTitle();
        renderTextField();
        renderSubmit();
        renderLink();

    }

}
