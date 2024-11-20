package Controller;

import Model.Book;
import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewBook implements Operation {
    @Override
    public void operation(Database database, Scanner sc, User user) {
        System.out.println();
        String select = "SELECT * FROM `book`;";
        ArrayList<Book> books = new ArrayList<>();
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()){
                Book book = new Book();
                book.setID(rs.getInt("ID"));
                book.setName(rs.getString("Name"));
                book.setAuthor(rs.getString("Author"));
                book.setPublisher(rs.getString("Publisher"));
                book.setCount(rs.getInt("Count"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Book b : books){
            if(b.isAvailable() < 2){

            }
        }
        System.out.println();
    }
}
