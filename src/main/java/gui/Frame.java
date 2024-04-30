package gui;

import gui.components.panels.FooterPanel;
import gui.components.panels.LoginPanel;
import gui.components.labels.Title;
import gui.components.panels.HeaderPanel;
import gui.components.panels.UserProfile;
import org.mybank.Bank;
import org.mybank.User;
import router.Router;

import javax.swing.*;
import java.awt.*;

public class Frame  extends javax.swing.JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private JPanel header;
    private static JPanel body;
    private JPanel footer;
    public Frame() {
        this.style();
        this.setVisible(true);
    }
    private void clearHeader(){
        if(header != null){
            this.remove(header);
            this.revalidate();
            this.repaint();
        }
    }
    public void addHeader(JPanel panel){
        clearHeader();
        header = panel;
        renderComponent();
    }

    private void clearBody(){
        if(body != null){
        this.remove(body);
        this.revalidate();
        this.repaint();
        }
    }
    public void addBody(JPanel panel){
        clearBody();
        body = panel;
        renderComponent();

    }
    private void clearFooter(){
        if(footer != null){
            this.remove(footer);
            this.revalidate();
            this.repaint();
        }
    }
    public void addFooter(JPanel panel){
        clearFooter();
        footer = panel;
        renderComponent();

    }
    public void style (){
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setTitle("Bank Management");
        super.setIconImage(new ImageIcon("src/main/resources/logo-small.png").getImage());
        super.getContentPane().setLayout(new BorderLayout());
        super.getContentPane().setBackground(Color.red);
        super.setResizable(false);
        super.setSize(WIDTH, HEIGHT);
    }
    public void renderComponent (){
        if(header != null) this.add(header, BorderLayout.NORTH);
        if(body != null) this.add(body, BorderLayout.CENTER);
        if(footer != null) this.add(footer, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }


}
