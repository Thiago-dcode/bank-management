package gui.components.labels;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextField extends JPanel {
    public JLabel label;
     public JTextField inputField;
    public TextField(int x, int y, int w, int h,String label, KeyListener keyListener) {

        super.setLayout(null);
        super.setBounds(x, y, w, h+25);
        renderComponents(x,y,w,h,label,keyListener);

    }
    public void renderComponents(int x, int y, int w, int h,String labelTxt, KeyListener kl){
        label = new JLabel(labelTxt);
        label.setBounds(0, 0, w, h);
        this.inputField = new JTextField(16);
        this.inputField.setBounds(0, 25, w, h);
        this.inputField.addKeyListener(kl);
        super.add(label);
        super.add(this.inputField);
    }
}
