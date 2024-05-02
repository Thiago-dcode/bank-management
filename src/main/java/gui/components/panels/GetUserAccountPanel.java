package gui.components.panels;

import exceptions.UserNotFoundException;
import org.mybank.Account;
import org.mybank.Bank;
import org.mybank.User;
import router.Router;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class GetUserAccountPanel extends JPanel  {

    private final int accountId;
    private final gui.Frame frame;
    private final Bank bank;
    JPanel container;
    private String username;
    public GetUserAccountPanel(Bank bank, gui.Frame frame, int accountId) {
        this.bank = bank;
        this.accountId = accountId;
        this.frame = frame;
        style();
        renderComponents();
    }


    public void style(){

        super.setLayout(new BorderLayout());
       super.setBorder(BorderFactory.createLineBorder(Color.black,5));

    }

    public void renderComponents(){
        renderUsers();

    }
    public void clearContainer(){
        if(container == null)return;
        super.remove(container);
        super.revalidate();
        super.repaint();
    }
    public void renderUsers(){
        clearContainer();
        container = new JPanel();
        container.setAlignmentY(CENTER_ALIGNMENT);
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.setBorder(BorderFactory.createTitledBorder("Choose a user to transfer"));
        List<User> users = User.getAll();

        for(User user : users){
            JButton button = new JButton(user.getName());
            button.addActionListener((e)->{
                username = user.getName();
                renderAccounts();
            });
            button.setActionCommand(user.getName());
            container.add(button);
        }
        super.add(container, BorderLayout.CENTER);
    }
    public void renderAccounts(){
        clearContainer();
        container = new JPanel();
        container.setAlignmentY(CENTER_ALIGNMENT);
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.setBorder(BorderFactory.createTitledBorder("Choose a user account to transfer"));
        List<Account> accounts = Bank.getUserAccounts(username);
        for(Account account : accounts){
            if(account.getId() == accountId) continue;
            JButton button = new JButton(account.getName());
            button.addActionListener((e)->{
                System.out.println(account.getId());
                frame.addBody(new TransferPanel(accountId,bank,username,account.getId()));
            });
            button.setActionCommand(account.getId()+ "");
            container.add(button);
        }

        super.add(container, BorderLayout.CENTER);
    }

}
