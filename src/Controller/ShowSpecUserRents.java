package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowSpecUserRents implements Operation {
    @Override
    public void operation(Database database, Scanner sc, User user) {
        System.out.println("Enter User ID (int): (-1 to show all users)");
        int ID = sc.nextInt();
        while(ID == -1) {
            printUsers(database);
            System.out.println("Enter User ID (int): (-1 to show all users)");
            ID = sc.nextInt();
        }
        new ShowUserRents(ID).operation(database, sc, user);
    }

    private void printUsers(Database database) {
        try{
            ResultSet rs = database.getStatement().executeQuery("SELECT * FROM `user`;");
            while(rs.next()) {
                int accType = rs.getInt("type");
                if(accType == 0) {
                    System.out.println("ID:\t\t\t\t" + rs.getInt("id"));
                    System.out.println("First Name:\t\t" + rs.getString("firstname"));
                    System.out.println("Last Name:\t\t" + rs.getString("lastname"));
                    System.out.println("Email:\t\t\t" + rs.getString("email"));
                    System.out.println("Phone Number:\t" + rs.getString("phonenumber"));
                    System.out.println("------------------------------------");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
