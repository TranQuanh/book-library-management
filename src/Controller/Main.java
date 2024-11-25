package Controller;
import Model.Database;
import Model.User;
import Model.Client;
import Model.Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        JFrame frame = new JFrame("Login");
        frame.setSize(600,330);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(250,206,27));
        frame.setLayout(new BorderLayout());
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Welcome to Book Library Management");
//        System.out.println("Enter your email:\n(-1) to create new account");
//        String email = sc.next();
//        if (email.equals("-1")){
//            new AddNewAccount(0).operation(database,sc,null);
//            return;
//        }
//        System.out.println("Enter password:");
//        String password = sc.next();

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
                } else{
                    System.out.println("Account does not exist!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean loggedIn =false;
        for(User u : users) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("Welcome "+u.getFirstName()+"!");
                loggedIn = true;
                u.showList(database,sc);
            }
        }
        if(!loggedIn) {
            System.out.println("Email or password doesn't match");
        }
    }
}