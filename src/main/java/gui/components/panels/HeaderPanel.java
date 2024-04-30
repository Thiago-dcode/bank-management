package gui.components.panels;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    public static final int HEIGHT = 50;
    public HeaderPanel() {

        this.style();
        this.renderComponents();
    }
    public HeaderPanel(Color bgColor) {
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
