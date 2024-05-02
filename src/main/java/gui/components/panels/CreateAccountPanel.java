package gui.components.panels;

import exceptions.UnauthorizedException;
import gui.components.labels.TextField;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CreateAccountPanel  extends javax.swing.JPanel {
    private  Bank bank;
    TextField txtAccountName;
    TextField txtAccountBalance;
    public CreateAccountPanel(Bank bank) {
        this.bank = bank;
        style();
        renderComponents();
    }
    public void style(){

        super.setLayout(null);
        super.setBounds(0, 100, 100, 100);
        super.setBorder(BorderFactory.createLineBorder(Color.red, 5));
    }

    public void renderComponents(){
         txtAccountName = new TextField(150,100,200,20,"Name",null);
         txtAccountBalance = new TextField(150,150,200,20,"Balance",new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }

            }
        });
        JButton btnCreate = new JButton("Create");
        btnCreate.setBounds(150,200,100,30);
        btnCreate.addActionListener((e)->{
            if(txtAccountName.inputField.getText().isEmpty())return;
            double amount =Double.parseDouble(txtAccountBalance.inputField.getText());
            if(amount < 0 || amount > 10000){
                JOptionPane.showMessageDialog(null,"Amount must be between 0 and 10000","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                bank.createAccount(txtAccountName.inputField.getText(),amount);
                Router.get("user");
            } catch (UnauthorizedException ex) {
                Router.get("login");
            }
        });
        super.add(txtAccountName);
        super.add(txtAccountBalance);
        super.add(btnCreate);

    }

}
