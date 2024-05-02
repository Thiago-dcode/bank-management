package gui.components.panels;

import org.mybank.Account;
import org.mybank.Bank;
import org.mybank.transaction.Transaction;
import org.mybank.transaction.TransferTransaction;
import router.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends javax.swing.JPanel implements ActionListener {
    private final Account account;

    public AccountPanel(Account account) {

        this.account = account;

        style();
        renderComponents();

    }

    public void style() {
        super.setLayout(new BorderLayout(20, 20));

        super.setBorder(BorderFactory.createLineBorder(Color.black, 5));
    }

    public void renderComponents() {

        JLabel label = new JLabel(account.toString());
        label.setBounds(100, 0, 400, 30);
        label.setHorizontalAlignment(JLabel.CENTER);
        super.add(label, BorderLayout.NORTH);
        renderOperations();
        renderTransactions();

    }

    public void renderOperations() {
        JPanel content = new JPanel();
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.setAlignmentY(Component.CENTER_ALIGNMENT);
        content.setBorder(BorderFactory.createTitledBorder("Operations"));
        content.setPreferredSize(new Dimension(500, 80));
        content.setMaximumSize(new Dimension(500, 80));
        JButton depBtn = new JButton("Deposit");
        depBtn.addActionListener(this);
        JButton witBtn = new JButton("Withdraw");
        witBtn.addActionListener(this);
        JButton traBtn = new JButton("Transfer");
        traBtn.addActionListener(this);
        content.add(depBtn);
        content.add(witBtn);
        content.add(traBtn);
        this.add(content, BorderLayout.SOUTH);
    }

    public void renderTransactions() {

        JPanel content = new JPanel();
        content.setAlignmentY(Component.CENTER_ALIGNMENT);
        content.setBorder(BorderFactory.createTitledBorder("Transaction"));
        content.setPreferredSize(new Dimension(400, 300));
        content.setMaximumSize(new Dimension(400, 300));
        for (Transaction transaction : account.getTransactions()) {
            ;

            JLabel label = new JLabel(transaction.toString());
                     label.setForeground(Color.black);
            label.setFont(new Font("Monospace", Font.PLAIN, 12));
            content.add(label);

        }
        JScrollPane scrollPane = new JScrollPane(content);
        super.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand().toLowerCase());
        switch (e.getActionCommand().toLowerCase()) {
            case "deposit":
                Router.get("deposit", account.getId() + "");
                return;
            case "withdraw":
                Router.get("withdraw", account.getId() + "");
                return;
            case "transfer":
                Router.get("transfer", account.getId() + "");

        }
    }
}
