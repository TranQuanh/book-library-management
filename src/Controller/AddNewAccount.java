package Controller;

import java.awt.*;
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

public class AddNewAccount implements Operation {
    private int accType;
    public AddNewAccount(int accType) {
        this.accType = accType;
    }

    @Override
    public void operation(Database database, JFrame f, User user){
        JFrame frame = new JFrame("Create New Account");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Book Management System",35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(7,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("First Name:",22));
        JTextField firstName = new JTextField(22);
        panel.add(firstName);

        panel.add(new JLabel("Last Name:",22));
        JTextField lastName = new JTextField(22);
        panel.add(lastName);

        panel.add(new JLabel("Email:",22));
        JTextField email = new JTextField(22);
        panel.add(email);

        panel.add(new JLabel("Phone Number:",22));
        JTextField phone = new JTextField(22);
        panel.add(phone);

        panel.add(new JLabel("Password:",22));
        JTextField password = new JTextField(22);
        panel.add(password);

        panel.add(new JLabel("Confirm Password:",22));
        JTextField confirmPassword = new JTextField(22);
        panel.add(confirmPassword);

        JButton login = new JButton("Login",22);
        panel.add(login);

        JButton createAcc = new JButton("Create Account",22);
        panel.add(createAcc);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);


//        System.out.println("Enter Firstname: ");
//        String firstname = sc.next();
//        System.out.println("Enter Lastname: ");
//        String lastname = sc.next();
//        System.out.println("Enter Email: ");
//        String email = sc.next();
//        System.out.println("Enter Phone Number: ");
//        String phone = sc.next();
//        System.out.println("Enter Password: ");
//        String password = sc.next();
//        System.out.println("Confirm Password: ");
//        String confirmPassword = sc.next();
//        while(!confirmPassword.equals(password)){
//            System.out.println("Password does not match!");
//            System.out.println("Enter Password: ");
//            password = sc.nextLine();
//            System.out.println("Confirm Password: ");
//            confirmPassword = sc.nextLine();
//        }
//
//        try{
//            //Check valid email
//            ArrayList<String> emails = new ArrayList<>();
//            ResultSet rs0 = database.getStatement().executeQuery("SELECT `Email` FROM `user`;");
//            while(rs0.next()){
//                emails.add(rs0.getString("Email"));
//            }
//
//            if (emails.contains(email)){
//                System.out.println("Email Already Exists");
//                return;
//            }
//
//            ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM user");
//            rs.next();
//            int ID = rs.getInt("count") + 1;
//            String insert = "INSERT INTO `user`(`id`,`firstname`,`lastname`, " +
//                    " `email`,`phonenumber`,`password`,`type`) VALUES " +
//                    " ('"+ID+"', '"+firstname+"', '"+lastname+"', '"+email+"'," +
//                    "'"+phone+"','"+password+"','"+accType+"');";
//            database.getStatement().execute(insert);
//            System.out.println("Account created succesfully!\n");
//
//            if (accType ==0){
//                user = new Client();
//                user.setID(ID);
//                user.setFirstName(firstname);
//                user.setLastName(lastname);
//                user.setEmail(email);
//                user.setPhoneNumber(phone);
//                user.setPassword(password);
//                user.showList(database,sc);
//            }
//
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
    }
}
