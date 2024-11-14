package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

import Model.Database;
import Model.User;
import Model.Operation;

public class AddNewAdmin implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user){
        System.out.println("Enter ID: ");
        int id = Integer.parseInt(s.next());
        System.out.println("Enter Name: ");
        String name = s.nextLine();
        System.out.println("Enter Email: ");
        String email = s.nextLine();
        System.out.println("Enter Phone Number: ");
        String phone = s.nextLine();
        System.out.println("Enter Address: ");
        String address = s.nextLine();
        System.out.println("Enter Username: ");
        String username = s.nextLine();
        System.out.println("Enter Password: ");
        String password = s.nextLine();
        System.out.println("Confirm Password: ");
        String confirmPassword = s.nextLine();
        while(!confirmPassword.equals(password)){
            System.out.println("Password does not match!");
            System.out.println("Enter Password: ");
            password = s.nextLine();
            System.out.println("Confirm Password: ");
            confirmPassword = s.nextLine();
        }

        int accType = 1;
        try{
            ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*)");
            rs.next();
            int ID = rs.getInt("COUNT(*)") - 1;
            String insert = "INSERT INTO `users`(`ID`,`Name`, " + " `Email`,`PhoneNumber`,`Password`,`Type`) VALUES " +
                    " ('"+id+"', '"+name+"', '"+email+"','"+phone+"','"+address+"','"+username+"','"+password+"','"+accType+"');";
            database.getStatement().execute(insert);
            System.out.println("Admin account created succesfully!\n");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
