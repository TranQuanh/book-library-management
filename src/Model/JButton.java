package Model;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class JButton extends javax.swing.JButton {
    private int borderRadius = 30;
    private Color defaultBackgroundColor = new Color(146, 171, 160); // Default background color
    private Color hoverBackgroundColor = new Color(101, 123, 119);

    public JButton(String text, int textSize, Color defaultColor, Color hoverColor) {
        super(text);
        setBackground(defaultColor);
        setFont(new Font("Inter 24pt Regular",Font.BOLD,textSize));
        setForeground(Color.white);
        setBorder(null);
        setFocusPainted(false); // Tắt hiệu ứng focus mặc định
        setContentAreaFilled(false); // Ngăn JButton tự vẽ nền

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor); // Revert to default color
            }
        });
    }

    public JButton(String text, int textSize) {
        super(text);
        setBackground(defaultBackgroundColor);
        setFont(new Font("Inter 24pt Regular",Font.BOLD,textSize));
        setForeground(Color.white);
        setBorder(null);
        setFocusPainted(false); // Tắt hiệu ứng focus mặc định
        setContentAreaFilled(false); // Ngăn JButton tự vẽ nền

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackgroundColor); // Revert to default color
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền với góc bo tròn
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

        // Vẽ viền (nếu cần, có thể tùy chỉnh màu)
        g2d.setColor(getForeground());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);

        super.paintComponent(g);
    }
    public void setBorderRadius(int radius) {
        this.borderRadius = radius;
        repaint();
    }
}
