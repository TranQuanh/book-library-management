package Controller;
import Model.Database;
import Model.JLabel;
import Model.JButton;
import Model.JTextField;
import Model.JPasswordField;
import Model.User;
import Model.Client;
import Model.Admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Database database;

    public static void main(String[] args) {
        database = new Database();
        installFont();
        start();
    }
    public static void start() {
        JFrame frame = new JFrame("Login");
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Book Management System","Noto Serif Regular",40);
        title.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        title.setForeground(new Color(101, 123, 119));

        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        JLabel fieldLabel = new JLabel("Email address:", 28);
        fieldLabel.setForeground(new Color(101, 123, 119));
        panel.add(fieldLabel);

        JTextField email = new JTextField(28);
        email.setForeground(new Color(101, 123, 119));
        panel.add(email);

        fieldLabel = new JLabel("Password:", 28);
        fieldLabel.setForeground(new Color(101, 123, 119));
        panel.add(fieldLabel);

        JPasswordField password = new JPasswordField(28);
        password.setForeground(new Color(101, 123, 119));
        panel.add(password);

        JButton createAcc = new JButton("Create New Account", 28, Color.WHITE, new Color(237, 237, 237));
        createAcc.setForeground(new Color(101, 123, 119));
        createAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewAccount(0).operation(database, frame, null);
                frame.dispose();
            }
        });
        panel.add(createAcc);

        ArrayList<User> users = new ArrayList<>();
        try {
            String select = "SELECT * FROM `user`;";   // Fixed SQL syntax
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()) {
                User user;
                int ID = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String em = rs.getString("Email");  // Fixed to double quotes
                String phoneNumber = rs.getString("PhoneNumber");
                String pass = rs.getString("Password");  // Fixed typo

                int type = rs.getInt("Type");
                if (type == 0){
                    user = new Client();
                    user.setID(ID);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(em);
                    user.setPhoneNumber(phoneNumber);
                    user.setPassword(pass);
                    users.add(user);
                } else if (type == 1) {
                    user = new Admin();
                    user.setID(ID);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(em);
                    user.setPhoneNumber(phoneNumber);
                    user.setPassword(pass);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton login = new JButton("Login",28);
        login.setForeground(Color.WHITE);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(email.getText().equals((""))){
                    JOptionPane.showMessageDialog(frame, "Email cannot be empty");
                    return;
                }
                if(password.getText().equals((""))){
                    JOptionPane.showMessageDialog(frame, "Password cannot be empty");
                    return;
                }
                boolean loggedIn =false;
                for(User u : users) {
                    if(u.getEmail().equals(email.getText()) && u.getPassword().equals(password.getText())) {
                        loggedIn = true;
                        u.showList(database,frame);
                        frame.dispose();
                    }
                }
                if(!loggedIn) {
                    JOptionPane.showMessageDialog(frame, "Email or password doesn't match");
                }
            }
        });
        panel.add(login);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void installFont() {
        try {
            File fontFile1 = new File("fonts/NotoSerif-Regular.ttf");
            Font customFont = null;
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile1);

            // Tùy chỉnh kích thước
            customFont = customFont.deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);


            File fontFile2 = new File("fonts/Inter_24pt-Regular.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile2);
            customFont = customFont.deriveFont(28f);
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            System.err.println("Lỗi khi tải font: " + e.getMessage());
        }
    }
}