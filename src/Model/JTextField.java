package Model;

import java.awt.*;
@SuppressWarnings("serial")
public class JTextField extends javax.swing.JTextField{
    public JTextField(int textSize) {
        super();
        setFont(new Font("Verdana",Font.BOLD,textSize));
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(null);
    }
}
