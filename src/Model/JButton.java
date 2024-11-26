package Model;

import java.awt.*;
@SuppressWarnings("serial")
public class JButton extends javax.swing.JButton {
    public JButton(String text, int textSize){
        super(text);
        setBackground(Color.black);
        setFont(new Font("Verdana",Font.BOLD,textSize));
        setForeground(Color.white);
        setBorder(null);
    }
}
