package Model;

import Controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import Model.JLabel;

public class Client extends User {
    private Operation[] operations = new Operation[]{
            new ViewBook(),
            new RentBook(),
            new ReturnBook(),
            new ShowUserRents(-9999),
            new EditUserDataClient(),
            new ChangePasswordClient(),
            new Quit()
    };
    private JButton[] btns = new JButton[]{
            new JButton("View Books", 22),
            new JButton("Rent Book", 22),
            new JButton("Return Book", 22),
            new JButton("Show My Rents", 22),
            new JButton("Edit My Data", 22),
            new JButton("Change Password", 22),
            new JButton("Quit", 22)
    };

    public Client() {
        super();
    }

    @Override
    public void showList(Database database, JFrame f) {
        JFrame frame = new JFrame("Client Panel");
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(f);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Thêm icon cho JFrame
        Image icon = new ImageIcon("icon\\chillguy.png").getImage(); // Đường dẫn đến file icon
        frame.setIconImage(icon);

        // Tạo panel có ảnh nền
        JPanel backgroundPanel = new JPanel() {
            private Image bgImage = new ImageIcon("image\\4907599.jpg").getImage(); // Đường dẫn ảnh

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f)); // Làm mờ 80%
                g2d.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                g2d.dispose();
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Tiêu đề
        JLabel title = new JLabel("Welcome User: " + getFirstName(), 22);
        title.setFont(new Font("Noto Serif Regular", Font.BOLD, 40));
        title.setForeground(new Color(101, 123, 119));
        title.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        backgroundPanel.add(title, BorderLayout.NORTH);

        // Tạo layout cho các nút
        JPanel buttonPanel = new JPanel(new GridLayout(btns.length, 1, 15, 15));
        buttonPanel.setOpaque(false); // Đảm bảo không che nền
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < btns.length; i++) {
            final int j = i;
            JButton button = btns[i];
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    operations[j].operation(database, frame, Client.this);
                }
            });
        }

        JPanel layout = new JPanel(new BorderLayout());
        layout.setOpaque(false);
        layout.add(buttonPanel, BorderLayout.CENTER);
        layout.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        backgroundPanel.add(layout, BorderLayout.CENTER);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

}