package Model;

import java.awt.*;

public class JPasswordField extends javax.swing.JPasswordField {

    public JPasswordField(int textSize) {
        super();
        setFont(new Font("Inter 24pt Regular",Font.BOLD,textSize));
        setHorizontalAlignment(JLabel.CENTER);
    }
}
