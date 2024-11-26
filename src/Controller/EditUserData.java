package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;

public class EditUserData implements Operation {
    @Override
    public void operation(Database database, Scanner sc, User user) {
        System.out.println("Enter First Name: (-1 to keep "+user.getFirstName()+")");
        String firstName = sc.next();
        if(firstName.equals("-1")) firstName = user.getFirstName();
        System.out.println("Enter Last Name: (-1 to keep "+user.getLastName()+")");
        String lastName = sc.next();
        if(lastName.equals("-1")) lastName = user.getLastName();
        System.out.println("Enter Email: (-1 to keep "+user.getEmail()+")");
        String email = sc.next();
        if(email.equals("-1")) email = user.getEmail();
        System.out.println("Enter Phone Number: (-1 to keep "+user.getPhoneNumber()+")");
        String phoneNumber = sc.next();
        if(phoneNumber.equals("-1")) phoneNumber = user.getPhoneNumber();
        String update = "UPDATE `user` SET `firstname` = '"+firstName+"', `lastname` = '"+lastName+"', " +
                "`email` = '"+email +"', `phonenumber` = '"+phoneNumber+ "'WHERE `ID` = '"+user.getID()+"';";
        try{
            database.getStatement().execute(update);
            System.out.println("Data update successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
