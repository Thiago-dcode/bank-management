package gui.components.labels;

import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {
    public Title(String title) {
        super(title);
        style();
    }
    public Title() {
        style();
    }
    public void style(){
        super.setHorizontalAlignment(JLabel.CENTER);
        super.setForeground(Color.white);
              super.setFont(new Font("Serif", Font.PLAIN, 30));
    }
}
