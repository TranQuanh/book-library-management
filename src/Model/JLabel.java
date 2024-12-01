package Model;

import java.awt.*;

@SuppressWarnings("serial")
public class JLabel extends javax.swing.JLabel {
    public JLabel(String text, int fontsize){
        super(text);
        setFont(new Font("Inter 24pt Regular",Font.BOLD,fontsize));
        setBackground(null);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public JLabel(String text, String font, int fontsize){
        super(text);
        setFont(new Font(font,Font.BOLD,fontsize));
        setBackground(null);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public JLabel(String text, String font, int fontsize, Color color){
        super(text);
        setFont(new Font(font,Font.BOLD,fontsize));
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public JLabel(String text, int fontsize, Color color){
        super(text);
        setFont(new Font("Inter 24pt Regular",Font.BOLD,fontsize));
        setBackground(null);
        setForeground(color);
        setHorizontalAlignment(JLabel.CENTER);
    }
}
