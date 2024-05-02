package gui.components.panels;

import exceptions.InvalidAmountException;
import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class TransactionPanel  extends JPanel implements ActionListener {
    protected final Bank bank;
    protected JTextField textField;
    protected final int accountId;
    protected  String transactionType;
    protected TransactionPanel(int accountId, Bank bank, String transactionType) {
        this.bank = bank;
        this.accountId = accountId;

        this.transactionType = transactionType;
        this.style();
        this.renderComponents();
    }

    public void style(){
        super.setLayout(new BorderLayout());
        super.setPreferredSize(new Dimension(Frame.WIDTH, HEIGHT));
    }

    public void renderComponents() {
        JLabel label = new JLabel(transactionType.toUpperCase() + " panel.");
        label.setHorizontalAlignment(JLabel.CENTER);
        super.add(label, BorderLayout.NORTH);
        renderTextField();

    }
    public void renderTextField(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel("Amount: ");
        label.setBounds(150,100,200,20);
        textField = new JTextField();
        textField.setBounds(150,120,150,20);
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }

            }
        });
        JButton addBtn = new JButton(transactionType);
        addBtn.addActionListener(this);
        addBtn.setBounds(200,140,100,20);
        panel.add(label);
        panel.add(textField);
        panel.add(addBtn);

        super.add(panel, BorderLayout.CENTER);

    }
}
