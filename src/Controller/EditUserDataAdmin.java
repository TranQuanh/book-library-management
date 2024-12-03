package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

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

public class EditUserDataAdmin implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user) {

        Color scheme1 = new Color(255, 208, 208);
        Color scheme2 = new Color(168, 118, 118);

        JFrame frame = new JFrame("Edit Data");
        frame.setSize(800,650);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Edit Data","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

        panel.add(new JLabel("First Name:",22,scheme2));
        JTextField firstName = new JTextField(22,Color.WHITE,scheme2);
        firstName.setText(user.getFirstName());
        panel.add(firstName);

        panel.add(new JLabel("Last Name:",22,scheme2));
        JTextField lastName = new JTextField(22,Color.WHITE,scheme2);
        lastName.setText(user.getLastName());
        panel.add(lastName);

        panel.add(new JLabel("Email:",22,scheme2));
        JTextField email = new JTextField(22,Color.WHITE,scheme2);
        email.setText(user.getEmail());
        panel.add(email);

        panel.add(new JLabel("Phone Number:",22,scheme2));
        JTextField phoneNumber = new JTextField(22,Color.WHITE,scheme2);
        phoneNumber.setText(user.getPhoneNumber());
        panel.add(phoneNumber);

        JButton cancel = new JButton("Cancel",22,new Color(233, 155, 155),scheme2);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        JButton confirm = new JButton("Confirm",22,new Color(233, 155, 155),scheme2);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if(firstName.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "First Name cannot be empty");
                    return;
                }
                if(lastName.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Last Name cannot be empty");
                    return;
                }
                if(email.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Email cannot be empty");
                    return;
                }
                if(phoneNumber.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Phone Number cannot be empty");
                    return;
                }

                String update = "UPDATE `admin` SET `first_name` = '"+firstName.getText()+"', `last_name` = '"+lastName.getText()+"', " +
                        "`email` = '"+email.getText() +"', `phone_number` = '"+phoneNumber.getText()+ "'WHERE `admin_id` = '"+user.getID()+"';";
                try{
                    database.getStatement().execute(update);
                    JOptionPane.showMessageDialog(frame, "Data updated successfully");
                    System.out.println("Data update successful");
                    user.setFirstName(firstName.getText());
                    user.setLastName(lastName.getText());
                    user.setEmail(email.getText());
                    user.setPhoneNumber(phoneNumber.getText());
                    frame.dispose();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        panel.add(confirm);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
