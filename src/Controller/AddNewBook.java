package Controller;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.*;
import java.sql.SQLException;
import Model.Database;
import Model.Operation;
import Model.User;

public class AddNewBook implements Operation {
    @Override
    public void operation(Database database, Scanner sc, User user){
        System.out.println("Enter name:");
        String name = sc.next();
        System.out.println("Enter author:");
        String author = sc.next();
        System.out.println("Enter publisher:");
        String publisher = sc.next();
        System.out.println("Enter ");
        int count = 0;
        try {
            ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM `book`");
            rs.next();
            int ID = rs.getInt("count") + 1;
            String insert = "INSERT INTO `user`(`ID`,`Name`,`Author`, " +
                    " `Publisher`,`Count`) VALUES " +
                    " ('"+ID+"', '"+name+"', '"+author+"', '"+publisher+"'," +
                    "'"+count+"');";
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
