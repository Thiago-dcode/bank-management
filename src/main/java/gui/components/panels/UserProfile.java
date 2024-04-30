package gui.components.panels;

import org.mybank.Account;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class UserProfile extends javax.swing.JPanel implements ActionListener {
  private   LinkedList<Account> accounts;
    private Bank bank;
    public UserProfile(Bank bank) {
        this.bank = bank;
        this.accounts = bank.getUserAccounts();

        style();
        this.renderComponents();
    }
    public void style(){
         super.setLayout(new BorderLayout());
        super.setBackground(Color.white);
        super.setBorder(BorderFactory.createLineBorder(Color.black,5));
    }
    public void renderComponents(){
        JLabel text = new JLabel("Hello " + bank.getUserLogin().getName());
        text.setFont(new Font("Serif", Font.PLAIN, 20));
        text.setForeground(Color.black);
        text.setHorizontalAlignment(JLabel.CENTER);
        this.add(text, BorderLayout.NORTH);
        renderAccounts();
    }
    public void renderAccounts(){
        JPanel content = new JPanel();
        content.setLayout(null);
        int y = 10;
        for (Account account : this.accounts) {
            JPanel container = new JPanel();
            container.setLayout(null);
            container.setBounds(0,y,500,40);
            container.setBackground(Color.red);
            y += 50;
            JLabel accountLabel = new JLabel("Account " + account.getId() + " "  + account.getName() +": " + account.getBalance());
            accountLabel.setBounds(10,10,300,20);
            accountLabel.setFont(new Font("Monospace", Font.PLAIN, 14));
            accountLabel.setForeground(Color.white);
            JButton accountButton = new JButton("Access");
            accountButton.setActionCommand( account.getId() +"");
            accountButton.setBounds(310,10,100,20);
            accountButton.addActionListener(this);
            container.add(accountButton);
            container.add(accountLabel);
            content.add(container);
            this.add(content,BorderLayout.CENTER);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    Router.get("account",e.getActionCommand());
    }
}
