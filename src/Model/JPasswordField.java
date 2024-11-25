package Model;

import java.awt.*;

public class JPasswordField extends javax.swing.JPasswordField {
    public JPasswordField(int textSize) {
        super();
        setFont(new Font("Verdana",Font.BOLD,textSize));
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(null);
    }
}
