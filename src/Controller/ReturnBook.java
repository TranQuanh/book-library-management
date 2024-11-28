package Controller;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;
public class ReturnBook implements Operation {
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("Return Book");
        frame.setSize(600,260);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Rent Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Rent ID: ",22));


//        System.out.println("Enter Rent ID(int): (-1 to show all rents)");
//        int ID = sc.nextInt();
//        while(ID==-1){
//            new ShowUserRents(user.getID()).operation(database, sc, user);
//            System.out.println("Enter Rent ID(int): (-1 to show all rents)");
//            ID = sc.nextInt();
//        }
//        try{
//            String select = "SELECT * FROM `rent` WHERE `ID` = '"+ID+"';";
//            ResultSet rs = database.getStatement().executeQuery(select);
//            rs.next();
//            Rent r = new Rent();
//            r.setID(rs.getInt("id"));
//            r.setUser(user);
//            r.setBorrowTime(rs.getString("borrowtime"));
//            r.setTotalDays(rs.getInt("totaldays"));
//            r.setStatus(rs.getInt("status"));
//
//            if(r.getStatusToString().equals("Delayed")){
//                System.out.println(r.getDelayedDays()+"delayed days");
//                System.out.println("You delayed");
//            }
//            String update = "UPDATE `rent` SET `status`='1' WHERE `id` = '" + ID + "';";
//            database.getStatement().execute(update);
//            System.out.println("Book returned successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
