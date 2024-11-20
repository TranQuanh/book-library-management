package Controller;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RentBook implements Operation {
    public void operation(Database database, Scanner sc, User user){
        System.out.println("Enter Book ID (int): (-1 to show all cars)");
        int bookID  = sc.nextInt();
        while(bookID == -1){
            new ViewBook().operation(database, sc, user);
            System.out.println("Enter Book ID (int): (-1 to show all cars)");
            bookID  = sc.nextInt();
        }

        System.out.println("Enter days (int):");
        int days = sc.nextInt();

        try{

            ResultSet rs0 = database.getStatement()
                    .executeQuery("SELECT * FROM `book` WHERE `ID` = '"+bookID+"';");
            rs0.next();
            Book book = new Book();
            book.setID(rs0.getInt("ID"));
            book.setName(rs0.getString("name"));
            book.setAuthor(rs0.getString("author"));
            book.setPublisher(rs0.getString("publisher"));
            book.setCount(rs0.getInt("count"));

            if(book.getCount() != 0) {
                System.out.println("Book isn't available!");
                return;
            }

            ResultSet rs1 = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM `rent`;");
            rs1.next();
            int ID = rs1.getInt("count") + 1;

            Rent rent = new Rent();

            String insert = "INSERT INTO `rent` (`ID`,`User`,`Book`,`DateTime`,`Days`,`Status`)" +
                    " VALUES('"+ID+"','"+user.getID()+"','"+book.getID()+"','"+rent.getBorrowTime()+"','"+days+"','"+0+"')";
            database.getStatement().execute(insert);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
