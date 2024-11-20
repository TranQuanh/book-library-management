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
        sc.nextLine();
        System.out.println("Enter name:");
        String name = sc.nextLine();
        System.out.println("Enter author:");
        String author = sc.nextLine();
        System.out.println("Enter publisher:");
        String publisher = sc.nextLine();
        System.out.println("Enter number of books");
        int count = sc.nextInt();
        try {
            ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM `book`");
            rs.next();
            int ID = rs.getInt("count") + 1;
            String insert = "INSERT INTO `book`(`id`,`name`,`author`, " +
                    " `publisher`,`count`) VALUES " +
                    " ('"+ID+"', '"+name+"', '"+author+"', '"+publisher+"'," +
                    "'"+count+"');";
            database.getStatement().executeUpdate(insert);
            System.out.println("Book added successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
