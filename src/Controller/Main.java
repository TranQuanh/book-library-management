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
        frame.setSize(750,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("PTIT's Online Library","Noto Serif Regular",40);
        title.setBorder(BorderFactory.createEmptyBorder(50, 50, 20, 50));
        title.setForeground(new Color(101, 123, 119));

        frame.add(title, BorderLayout.NORTH);

        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;


        JPanel panel = new JPanel(layout);
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,30,50));

        JLabel fieldLabel = new JLabel("Email address:", 28);
        fieldLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fieldLabel.setForeground(new Color(101, 123, 119));
        panel.add(fieldLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        JTextField email = new JTextField(28);
        email.setPreferredSize(new Dimension(200, 30));
        email.setForeground(new Color(101, 123, 119));
        email.setHorizontalAlignment(SwingConstants.LEFT);
        email.setBorder(BorderFactory.createCompoundBorder(
                email.getBorder(), // Border gốc của JTextField
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        panel.add(email,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        fieldLabel = new JLabel("Password:", 28);
        fieldLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fieldLabel.setForeground(new Color(101, 123, 119));
        panel.add(fieldLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;

        JPasswordField password = new JPasswordField(28);
        password.setPreferredSize(new Dimension(200, 30));
        password.setForeground(new Color(101, 123, 119));
        password.setHorizontalAlignment(SwingConstants.LEFT);
        password.setBorder(BorderFactory.createCompoundBorder(
                password.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        panel.add(password,gbc);

        JButton createAcc = new JButton("Create New Account", 28, Color.WHITE, new Color(237, 237, 237));
        createAcc.setForeground(new Color(101, 123, 119));
        createAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewClient().operation(database, frame, null);
                frame.dispose();
            }
        });
        panel.add(createAcc);

        ArrayList<User> users = new ArrayList<>();
        try {
            String select = "SELECT * FROM `admin`;";   // Fixed SQL syntax
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()) {
                int ID = rs.getInt("admin_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String em = rs.getString("email");  // Fixed to double quotes
                String phoneNumber = rs.getString("phone_number");
                String pass = rs.getString("password");  // Fixed typo


                User user = new Admin();
                user.setID(ID);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(em);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(pass);
                users.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String select = "SELECT * FROM `client`;";   // Fixed SQL syntax
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()) {
                int ID = rs.getInt("client_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String em = rs.getString("email");  // Fixed to double quotes
                String phoneNumber = rs.getString("phone_number");
                String pass = rs.getString("password");  // Fixed typo


                User user = new Client();
                user.setID(ID);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(em);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(pass);
                users.add(user);

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
                        u.showMenu(database,frame);
                        frame.dispose();
                    }
                }
                if(!loggedIn) {
                    JOptionPane.showMessageDialog(frame, "Email or password doesn't match");
                }
            }
        });
        gbc.insets = new Insets(30, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(login,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(createAcc,gbc);

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