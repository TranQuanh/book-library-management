package Controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

import java.util.Scanner;

public class ShowUserRents implements Operation {
    private int userId;
    public ShowUserRents(int userId) {
        this.userId = userId;
    }
    @Override
    public void operation(Database database, Scanner sc, User user) {
        int bookID;
        try{
            String select = "SELECT * FROM `rent` WHERE `userid` = '"+userId+"';";
            ResultSet rs = database.getStatement().executeQuery(select);
            ArrayList<Rent> rents = new ArrayList<>();
            while(rs.next()){
                Rent rent = new Rent();
                rent.setID(rs.getInt("id"));
                bookID = rs.getInt("bookid");
                rent.setBorrowTime(rs.getString("borrowtime"));
                rent.setTotalDays(rs.getInt("totaldays"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
