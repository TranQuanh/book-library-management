package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

import Model.Database;
import Model.User;
import Model.Operation;

public class AddNewClient implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user){
        System.out.println("Enter Firstname: ");
        String firstname = s.next();
        System.out.println("Enter Lastname: ");
        String lastname = s.next();
        System.out.println("Enter Email: ");
        String email = s.next();
        System.out.println("Enter Phone Number: ");
        String phone = s.next();
        System.out.println("Enter Password: ");
        String password = s.next();
        System.out.println("Confirm Password: ");
        String confirmPassword = s.next();
        while(!confirmPassword.equals(password)){
            System.out.println("Password does not match!");
            System.out.println("Enter Password: ");
            password = s.nextLine();
            System.out.println("Confirm Password: ");
            confirmPassword = s.nextLine();
        }

        int accType = 0;
        try{
            ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM user");
            rs.next();
            int ID = rs.getInt("count") + 1;
            String insert = "INSERT INTO `user`(`ID`,`FirstName`,`LastName`, " +
                    " `Email`,`PhoneNumber`,`Password`,`Type`) VALUES " +
                    " ('"+ID+"', '"+firstname+"', '"+lastname+"', '"+email+"'," +
                    "'"+phone+"','"+password+"','"+accType+"');";
            database.getStatement().execute(insert);
            System.out.println("Client account created succesfully!\n");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
