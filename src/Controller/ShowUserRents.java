package Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.*;

import javax.swing.*;
import java.util.Scanner;

public class ShowUserRents implements Operation {
    private int userId;
    public ShowUserRents(int userId) {
        this.userId = userId;
    }
    @Override
    public void operation(Database database, JFrame f, User user) {
        if (userId==-9999) userId = user.getID();
        ArrayList<Rent> rents = new ArrayList<>();
        ArrayList<Integer> bookIDs = new ArrayList<>();
        try{
            String select = "SELECT * FROM `rent` WHERE `userid` = '"+userId+"';";
            ResultSet rs = database.getStatement().executeQuery(select);
            while(rs.next()){
                Rent rent = new Rent();
                rent.setID(rs.getInt("id"));
                bookIDs.add(rs.getInt("bookid"));
                rent.setBorrowTime(rs.getString("borrowtime"));
                rent.setTotalDays(rs.getInt("totaldays"));
                rent.setStatus(rs.getInt("status"));
                rents.add(rent);
            }

            String selectUser = "SELECT * FROM `user` WHERE `ID` = '"+userId+"';";
            ResultSet rs2 = database.getStatement().executeQuery(selectUser);
            rs2.next();
            User u = new Client();
            u.setID(rs2.getInt("ID"));
            u.setFirstName(rs2.getString("firstname"));
            u.setLastName(rs2.getString("lastname"));
            u.setEmail(rs2.getString("email"));
            u.setPhoneNumber(rs2.getString("phonenumber"));
            u.setPassword(rs2.getString("password"));
            for(int j = 0; j < rents.size(); j++){
                Rent r = rents.get(j);
                r.setUser(u);
                ResultSet rs3 = database.getStatement()
                .executeQuery("SELECT * FROM `book` WHERE `ID` = '"+bookIDs.get(j)+"';");
                rs3.next();
                Book b = new Book();
                b.setID(rs3.getInt("id"));
                b.setName(rs3.getString("name"));
                b.setAuthor(rs3.getString("author"));
                b.setPublisher(rs3.getString("publisher"));
                b.setCount(rs3.getInt("count"));
                r.setBook(b);

                System.out.println("ID:\t\t"+ r.getID());
                System.out.println("Name:\t\t"+ r.getUser().getFirstName() + " " + r.getUser().getLastName());
                System.out.println("Email:\t\t"+ r.getUser().getEmail());
                System.out.println("Phone Number:\t\t"+ r.getUser().getPhoneNumber());
                System.out.println("Book ID:\t\t"+ r.getBook().getID());
                System.out.println("Book:\t\t"+ r.getBook().getName()+" "+r.getBook().getAuthor());
                System.out.println("Date time:\t\t"+ r.getBorrowTime());
                System.out.println("Total days:\t\t"+ r.getTotalDays());
                System.out.println("Status:\t\t"+ r.getStatusToString());
                System.out.println("------------------");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
