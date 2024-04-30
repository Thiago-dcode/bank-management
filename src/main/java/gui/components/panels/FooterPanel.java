package gui.components.panels;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends JPanel {
    public static final int HEIGHT = 50;
    public FooterPanel() {

        this.style();
        this.renderComponents();
    }
    public FooterPanel(Color bgColor) {
        this.style();
        super.setBackground(bgColor);
        this.renderComponents();
    }
    public void style(){

        super.setLayout(new BorderLayout());
        super.setPreferredSize(new Dimension(Frame.WIDTH, HEIGHT));
    }

    public void renderComponents() {}
}
