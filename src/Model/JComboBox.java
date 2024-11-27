package Model;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("rawtypes")
public class JComboBox extends javax.swing.JComboBox {
    @SuppressWarnings("unchecked")
    public JComboBox(String[] items, int fontSize) {
        super(items);
        setFont(new Font("SansSerif", Font.BOLD, fontSize));
        setBackground(Color.black);
        ((JPanel) getRenderer()).setAlignmentX(SwingConstants.CENTER);
    }
}
