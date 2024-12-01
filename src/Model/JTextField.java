package Model;

import java.awt.*;
@SuppressWarnings("serial")
public class JTextField extends javax.swing.JTextField{
    public JTextField(int textSize) {
        super();
        setFont(new Font("Inter 24pt Regular",Font.BOLD,textSize));
        setHorizontalAlignment(JLabel.CENTER);
        setBackground(Color.white);
//        setBorder(null);
    }

    public JTextField(int textSize,Color bgColor, Color fgColor) {
        super();
        setFont(new Font("Inter 24pt Regular",Font.BOLD,textSize));
        setHorizontalAlignment(JLabel.CENTER);
        setBackground(bgColor);
        setForeground(fgColor);
    }
}
