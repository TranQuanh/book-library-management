package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

import Model.*;

public class AddNewAccount implements Operation {
    private int accType;
    public AddNewAccount(int accType) {
        this.accType = accType;
    }

    @Override
    public void operation(Database database, Scanner sc, User user){
        System.out.println("Enter Firstname: ");
        String firstname = sc.next();
        System.out.println("Enter Lastname: ");
        String lastname = sc.next();
        System.out.println("Enter Email: ");
        String email = sc.next();
        System.out.println("Enter Phone Number: ");
        String phone = sc.next();
        System.out.println("Enter Password: ");
        String password = sc.next();
        System.out.println("Confirm Password: ");
        String confirmPassword = sc.next();
        while(!confirmPassword.equals(password)){
            System.out.println("Password does not match!");
            System.out.println("Enter Password: ");
            password = sc.nextLine();
            System.out.println("Confirm Password: ");
            confirmPassword = sc.nextLine();
        }

        try{
            ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM user");
            rs.next();
            int ID = rs.getInt("count") + 1;
            String insert = "INSERT INTO `user`(`ID`,`FirstName`,`LastName`, " +
                    " `Email`,`PhoneNumber`,`Password`,`Type`) VALUES " +
                    " ('"+ID+"', '"+firstname+"', '"+lastname+"', '"+email+"'," +
                    "'"+phone+"','"+password+"','"+accType+"');";
            database.getStatement().execute(insert);
            System.out.println("Account created succesfully!\n");

            if (accType ==0){
                user = new Client();
                user.setID(ID);
                user.setFirstName(firstname);
                user.setLastName(lastname);
                user.setEmail(email);
                user.setPhoneNumber(phone);
                user.setPassword(password);
                user.showList(database,sc);
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
