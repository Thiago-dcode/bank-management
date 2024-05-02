package gui.components.panels;

import exceptions.DuplicateEntityException;
import exceptions.UserNotFoundException;
import gui.components.labels.TextField;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterPanel extends AuthPanel{


    TextField ageField;

    public RegisterPanel(Bank bank) {
      super(bank,"Register");
         renderTitle();
      renderTextField();
      renderAgeField();
      renderSubmit(200);
      renderLink();
    }

    public void renderAgeField(){
        ageField = new TextField(150,150,200,20,"Age: ",new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }

            }
        });
        super.add(ageField);
//        super.add(textField);
//        JLabel label = new JLabel("Age: ");
//        label.setBounds(150,150,150,20);
//        ageField = new JTextField();
//        ageField.setBounds(150,175,200,20);
//        ageField.addKeyListener(new KeyAdapter() {
//            public void keyTyped(KeyEvent e) {
//                char c = e.getKeyChar();
//                if(!Character.isDigit(c)){
//                    e.consume();
//                }
//
//            }
//        });
//        super.add(label);
//        super.add(ageField);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(textField.inputField.getText().isEmpty() || ageField.inputField.getText().isEmpty())return;
            int age = Integer.parseInt(ageField.inputField.getText());
            System.out.println(age);
            if(age < 0 || age > 100){
                JOptionPane.showMessageDialog(null,"Age must be between 0 and 100");
                return;
            }
            bank.registerUser(textField.inputField.getText(),age);
            //success
            Router.get("login");
        }
        catch(DuplicateEntityException une){
            this.errorLabel.setText(une.getMessage());
        }
    }
}
