package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import Model.*;
import Model.JButton;
import Model.JLabel;
import Model.JTextField;

import javax.swing.*;
import Model.JPasswordField;

public class AddNewClient implements Operation {
    public AddNewClient() {
    }

    @Override
    public void operation(Database database, JFrame f, User user){
        Color scheme1 = new Color(255, 208, 208);
        Color scheme2 = new Color(168, 118, 118);

        JFrame frame = new JFrame("Create New Account");
        frame.setSize(1000,800);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Book Management System","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(7,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

        panel.add(new JLabel("First Name:",22,scheme2));
        JTextField firstname = new JTextField(22,Color.WHITE,scheme2);
        panel.add(firstname);

        panel.add(new JLabel("Last Name:",22,scheme2));
        JTextField lastname = new JTextField(22,Color.WHITE,scheme2);
        panel.add(lastname);

        panel.add(new JLabel("Email:",22,scheme2));
        JTextField email = new JTextField(22,Color.WHITE,scheme2);
        panel.add(email);

        panel.add(new JLabel("Phone Number:",22,scheme2));
        JTextField phone = new JTextField(22,Color.WHITE,scheme2);
        panel.add(phone);

        panel.add(new JLabel("Password:",22,scheme2));
        JPasswordField password = new JPasswordField(22);
        password.setForeground(scheme2);
        panel.add(password);

        panel.add(new JLabel("Confirm Password:",22,scheme2));
        JPasswordField confirmPassword = new JPasswordField(22);
        confirmPassword.setForeground(scheme2);
        panel.add(confirmPassword);

        JButton login = new JButton("Login",22,new Color(233, 155, 155),scheme2);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.start();
                frame.dispose();
            }
        });
        panel.add(login);

        JButton createAcc = new JButton("Create Account",22,new Color(233, 155, 155),scheme2);
        createAcc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstname.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "First Name cannot be empty");
                    return;
                }
                if (lastname.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Last Name cannot be empty");
                    return;
                }
                if (email.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Email cannot be empty");
                    return;
                }
                if (phone.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Phone Number cannot be empty");
                    return;
                }
                if (password.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Password cannot be empty");
                    return;
                }
                if (confirmPassword.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Confirm Password cannot be empty");
                    return;
                }
                if (!password.getText().equals(confirmPassword.getText())) {
                    JOptionPane.showMessageDialog(frame, "Password does not match");
                    return;
                }
                try {
                    ArrayList<String> emails = new ArrayList<>();
                    ResultSet rs0 = database.getStatement().executeQuery("SELECT `email` FROM `admin` UNION SELECT `email` FROM `client`;");
                    while (rs0.next()) {
                        emails.add(rs0.getString("email"));
                    }

                    if (emails.contains(email.getText())) {
                        JOptionPane.showMessageDialog(frame, "Email Already Exists");
                        return;
                    }


                    String insert = "INSERT INTO `client`(`first_name`,`last_name`, " +
                            " `email`,`phone_number`,`password`) VALUES " +
                            " ('" + firstname.getText() + "', '" + lastname.getText() + "', '" + email.getText() + "'," +
                            "'" + phone.getText() + "','" + password.getText() + "');";
                    database.getStatement().execute(insert);
                    JOptionPane.showMessageDialog(frame, "Account Created Successfully");
                    frame.dispose();
                    User user = new Client();
                    ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM client");
                    rs.next();
                    int ID = rs.getInt("count") ;
                    user.setID(ID);
                    user.setFirstName(firstname.getText());
                    user.setLastName(lastname.getText());
                    user.setEmail(email.getText());
                    user.setPhoneNumber(phone.getText());
                    user.setPassword(password.getText());
                    user.showList(database, frame);
                } catch (SQLException e1) {
                    System.out.println("123");
                    JOptionPane.showMessageDialog(frame, e1.getMessage());
                }
            }

        });
        panel.add(createAcc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
