package Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Model.Book;
import Model.Database;
import Model.Operation;
import Model.User;

public class UpdateBook implements Operation {

    @Override
    public void operation(Database database, Scanner sc, User user) {
        System.out.println("Enter book ID(int):(-1 to show all books)");
        int ID = sc.nextInt();
        while(ID == -1){
            new ViewBook().operation(database, sc, user);
            System.out.println("Enter book ID(int):(-1 to show all books)");
            ID = sc.nextInt();
        }
        try{
            ResultSet rs1 = database.getStatement()
                    .executeQuery("SELECT * FROM `book` WHERE `ID` = '"+ID+"';");
            rs1.next();
            Book book = new Book();
            book.setID(rs1.getInt("ID"));
            book.setName(rs1.getString("name"));
            book.setAuthor(rs1.getString("author"));
            book.setPublisher(rs1.getString("publisher"));
            book.setCount(rs1.getInt("count"));

            if(book.getCount() == 0) {
                System.out.println("Book does not exist");
                return;
            }

            System.out.println("Enter Name: (-1: "+book.getName()+")");
            String name = sc.next();
            if(name.equals("-1")) name = book.getName();

            System.out.println("Enter Author: (-1: "+book.getAuthor()+")");
            String author = sc.next();
            if(author.equals("-1")) author = book.getAuthor();

            System.out.println("Enter Publisher: (-1: "+book.getPublisher()+")");
            String publisher = sc.next();
            if(publisher.equals("-1")) publisher = book.getPublisher();

            String update = "UPDATE `book` SET `name` = '"+name+"', `author` = '"+author+"', " +
                    "`publisher` = '"+publisher+"'," + "WHERE `ID` = '"+ID+"';";

            database.getStatement().executeUpdate(update);
            System.out.println("Book updated successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
