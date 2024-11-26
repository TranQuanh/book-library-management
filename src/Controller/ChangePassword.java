package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import Model.JButton;
import Model.JLabel;
import Model.JPasswordField;
import Model.JTextField;

public class ChangePassword implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("Change Password");
        frame.setSize(600,380);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250,206,27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Change Password",35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout (5,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Old Password:",22));
        JPasswordField oldPassword = new JPasswordField(22);
        panel.add(oldPassword);
        panel.add(new JLabel("New Password:",22));
        JPasswordField newPassword = new JPasswordField(22);
        panel.add(newPassword);

        panel.add(new JLabel("Confirm Password:",22));
        JPasswordField confirmPassword = new JPasswordField(22);
        panel.add(confirmPassword);

        JButton cancel = new JButton("Cancel", 22);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        JButton confirm = new JButton("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if(oldPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Old Password cannot be empty");
                    return;
                }
                if(newPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Old Password cannot be empty");
                    return;
                }
                if(confirmPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Old Password cannot be empty");
                    return;
                }
                if(!oldPassword.getText().equals(user.getPassword())){
                    JOptionPane.showMessageDialog(frame, "Incorrect Old Password");
                    return;
                }
                if(!newPassword.getText().equals(confirmPassword.getText())){
                    JOptionPane.showMessageDialog(frame, "Passwords doesn't match");
                    return;
                }
                try{
                    String update = "UPDATE `user` SET" +
                            " `password`='"+newPassword.getText()+"' WHERE `ID` = '"+user.getID()+"'";
                    database.getStatement().execute(update);
                    JOptionPane.showMessageDialog(frame, "Password changed successfully");
                    System.out.println("Password changed successfully!");
                    user.setPassword(newPassword.getText());
                    frame.dispose();
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(frame, e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        panel.add(confirm);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);



    }
}
