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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Database database;

    public static void main(String[] args) {
        database = new Database();
        start();
    }
    public static void start() {
        JFrame frame = new JFrame("Login");
        frame.setSize(800,340);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(27, 250, 183));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Book Management System",35);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Email:",22));

        JTextField email = new JTextField(22);
        panel.add(email);

        panel.add(new JLabel("Password:",22));

        JPasswordField password = new JPasswordField(22);
        panel.add(password);

        JButton createAcc = new JButton("Create New Account",22);
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
                } else{
                    System.out.println("Account does not exist!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton login = new JButton("Login",22);
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

//        ArrayList<User> users = new ArrayList<>();
//        try {
//            String select = "SELECT * FROM `user`;";   // Fixed SQL syntax
//            ResultSet rs = database.getStatement().executeQuery(select);
//            while (rs.next()) {
//                User user;
//                int ID = rs.getInt("ID");
//                String firstName = rs.getString("FirstName");
//                String lastName = rs.getString("LastName");
//                String em = rs.getString("Email");  // Fixed to double quotes
//                String phoneNumber = rs.getString("PhoneNumber");
//                String pass = rs.getString("Password");  // Fixed typo
//
//                int type = rs.getInt("Type");
//                if (type == 0){
//                    user = new Client();
//                    user.setID(ID);
//                    user.setFirstName(firstName);
//                    user.setLastName(lastName);
//                    user.setEmail(em);
//                    user.setPhoneNumber(phoneNumber);
//                    user.setPassword(pass);
//                    users.add(user);
//                } else if (type == 1) {
//                    user = new Admin();
//                    user.setID(ID);
//                    user.setFirstName(firstName);
//                    user.setLastName(lastName);
//                    user.setEmail(em);
//                    user.setPhoneNumber(phoneNumber);
//                    user.setPassword(pass);
//                    users.add(user);
//                } else{
//                    System.out.println("Account does not exist!");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        boolean loggedIn =false;
//        for(User u : users) {
//            if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
//                System.out.println("Welcome "+u.getFirstName()+"!");
//                loggedIn = true;
// //               u.showList(database,sc);
//            }
//        }
//        if(!loggedIn) {
//            System.out.println("Email or password doesn't match");
//        }
    }
}