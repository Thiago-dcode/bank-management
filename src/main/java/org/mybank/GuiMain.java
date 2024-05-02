package org.mybank;

import exceptions.DuplicateEntityException;
import factory.AccountFactory;
import factory.DepositTransactionFactory;
import factory.UserFactory;
import factory.WithdrawTransactionFactory;
import gui.Frame;
import gui.components.labels.Title;
import gui.components.panels.*;
import router.Router;

import javax.swing.*;
import java.awt.*;

public class GuiMain {
    public static void main(String[] args) throws DuplicateEntityException {
        Bank bank = new Bank("Santander","Calle Mayor 24");
         AccountFactory.createManyAccountsByUsers( UserFactory.createManyUsers(10),3);
        AccountFactory.createManyAccounts( bank.registerUser("thiago",30),3);
        DepositTransactionFactory.createManyDepositTransaction(Bank.getUserAccounts("thiago").getFirst(),5);
        WithdrawTransactionFactory.createManyWithdrawTransaction(Bank.getUserAccounts("thiago").getFirst(),5);
        DepositTransactionFactory.createManyDepositTransaction(Bank.getUserAccounts("thiago").get(1),5);
        WithdrawTransactionFactory.createManyWithdrawTransaction(Bank.getUserAccounts("thiago").get(1),5);
        DepositTransactionFactory.createManyDepositTransaction(Bank.getUserAccounts("thiago").getLast(),5);
        WithdrawTransactionFactory.createManyWithdrawTransaction(Bank.getUserAccounts("thiago").getLast(),5);
        Frame frame =  new Frame();
        JPanel header =new HeaderPanel(Color.red);
        FooterPanel footer = new FooterPanel(Color.red,bank);
        Title title = new Title();
        Router.add("login",(arg)->{
            title.setText("Welcome to: "+ bank.getName());
            header.add(title);
            frame.addHeader(header);
            frame.addBody(new LoginPanel(bank));
            frame.addFooter(new FooterPanel(Color.red,bank,false));
        });
           Router.get("login");
        Router.add("register",(arg)->{
            frame.addBody(new RegisterPanel(bank));
            frame.addFooter(footer);
            frame.addFooter(new FooterPanel(Color.red,bank,false));
        });
        Router.add("user",(arg)->{
            title.setText("Welcome: "+ bank.getUserLogin().getName());
            header.add(title);
            frame.addHeader(header);
            frame.addBody(new UserProfile(bank));
            frame.addFooter(new FooterPanel(Color.red,bank));
        });
        Router.add("account",(arg)->{
           try {
               int accountId = Integer.parseInt(arg);
               Account account = bank.findAccount0rError(accountId);
               title.setText("Account: "+ account.getName());
               header.add(title);
               frame.addHeader(header);
               frame.addBody(new AccountPanel(account));
               JButton button = new JButton("Back");
               button.setBounds(200, 10, 100, 30);
               button.addActionListener((e) ->{
                   Router.get("user");
               });
               footer.add(button, BorderLayout.WEST);
               frame.addFooter(footer);
           }catch (Exception e){

               System.out.println("Something went wrong: "+e.getMessage());
           }
        });
        Router.add("deposit",(arg)->{
            try {
                frame.addBody(new DepositPanel(Integer.parseInt(arg),bank));

            }catch (Exception e){
                System.out.println("Something went wrong: "+e.getMessage());
            }
        });
        Router.add("withdraw",(arg)->{
            try {
                frame.addBody(new WithdrawPanel(Integer.parseInt(arg),bank));
            }catch (Exception e){
                System.out.println("Something went wrong: "+e.getMessage());
            }
        });
        Router.add("transfer",(arg)->{
            try {
                frame.addBody(new GetUserAccountPanel(bank,frame,Integer.parseInt(arg)));
            }catch (Exception e){
                System.out.println("Something went wrong: "+e.getMessage());
            }
        });

        Router.add("createAccount",(arg)->{
            title.setText("Create account");
            frame.addBody(new CreateAccountPanel(bank));
        });


    }
}
