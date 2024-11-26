package Model;

import java.awt.*;

@SuppressWarnings("serial")
public class JLabel extends javax.swing.JLabel {
    public JLabel(String text, int fontsize){
        super(text);
        setFont(new Font("Verdana",Font.BOLD,fontsize));
        setBackground(null);
        setHorizontalAlignment(JLabel.CENTER);

    }
}
