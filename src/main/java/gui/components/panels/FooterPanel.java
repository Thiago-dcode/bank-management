package gui.components.panels;

import org.mybank.Bank;
import router.Router;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {
    public static final int HEIGHT = 50;
    private Bank bank;
    public FooterPanel() {

        this.style();
        this.renderComponents();
    }
    public FooterPanel(Color bgColor,Bank bank) {
        this.style();
        this.bank = bank;
        super.setBackground(bgColor);
        this.renderComponents();
    }
    public FooterPanel(Color bgColor,Bank bank, boolean renderComponents) {
        this.style();
        this.bank = bank;
        super.setBackground(bgColor);
        if (renderComponents) {
            this.renderComponents();
        }
    }


    public void style(){

        super.setLayout(new BorderLayout());
        super.setPreferredSize(new Dimension(Frame.WIDTH, HEIGHT));
    }

    public void renderComponents() {
        JButton logout = new JButton("Logout");
        logout.addActionListener((e)->{
            bank.logOut();
            Router.get("login");
        });
        super.add(logout,BorderLayout.EAST);
    }
}
