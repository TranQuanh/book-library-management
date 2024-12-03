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

public class ChangePasswordAdmin implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user) {

        Color scheme1 = new Color(255, 208, 208);
        Color scheme2 = new Color(168, 118, 118);

        JFrame frame = new JFrame("Change Password");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Change Password","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout (4,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

        panel.add(new JLabel("Old Password:",22,scheme2));
        JPasswordField oldPassword = new JPasswordField(22);
        oldPassword.setForeground(scheme2);
        panel.add(oldPassword);
        panel.add(new JLabel("New Password:",22,scheme2));
        JPasswordField newPassword = new JPasswordField(22);
        newPassword.setForeground(scheme2);
        panel.add(newPassword);

        panel.add(new JLabel("Confirm Password:",22,scheme2));
        JPasswordField confirmPassword = new JPasswordField(22);
        confirmPassword.setForeground(scheme2);
        panel.add(confirmPassword);

        JButton cancel = new JButton("Cancel", 22,new Color(233, 155, 155),scheme2);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        JButton confirm = new JButton("Confirm", 22,new Color(233, 155, 155),scheme2);
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
                    String update = "UPDATE `admin` SET" +
                            " `password`='"+newPassword.getText()+"' WHERE `admin_id` = '"+user.getID()+"'";
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
