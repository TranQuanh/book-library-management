package Controller;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.*;
import Model.JLabel;
import Model.JTable;

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

        Color scheme1 = Color.WHITE;
        Color scheme2 = new Color(101, 123, 119);

        JFrame frame = new JFrame("Show User Rents");
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Rents","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));
        frame.add(title,BorderLayout.NORTH);

        String[] header = new String[]{
                "ID", "Name", "Email", "Phone Number", "Book ID", "Book", "Date time","Total days","Status"
        };
        ArrayList<Rent> rents = new ArrayList<>();
        ArrayList<Integer> bookIDs = new ArrayList<>();

        try{
            String select = "SELECT * FROM `rent` WHERE `client_id` = '"+userId+"';";
            ResultSet rs = database.getStatement().executeQuery(select);
            while(rs.next()){
                Rent rent = new Rent();
                rent.setID(rs.getInt("rent_id"));
                bookIDs.add(rs.getInt("book_id"));
                rent.setBorrowTime(rs.getString("rent_day"));
                rent.setTotalDays(rs.getInt("total_day"));
                rent.setStatus(rs.getInt("status"));
                rents.add(rent);
            }

            String selectUser = "SELECT * FROM `client` WHERE `client_id` = '"+userId+"';";
            ResultSet rs2 = database.getStatement().executeQuery(selectUser);
            rs2.next();
            User u = new Client();
            u.setID(rs2.getInt("client_id"));
            u.setFirstName(rs2.getString("first_name"));
            u.setLastName(rs2.getString("last_name"));
            u.setEmail(rs2.getString("email"));
            u.setPhoneNumber(rs2.getString("phone_number"));
            u.setPassword(rs2.getString("password"));
            for(int j = 0; j < rents.size(); j++){
                Rent r = rents.get(j);
                r.setUser(u);
                ResultSet rs3 = database.getStatement()
                .executeQuery("SELECT * FROM `book` WHERE `book_id` = '"+bookIDs.get(j)+"';");
                rs3.next();
                Book b = new Book();
                b.setID(rs3.getInt("book_id"));
                b.setName(rs3.getString("name"));
                b.setAuthor(rs3.getString("author"));
                b.setPublisher(rs3.getString("publisher"));
                b.setCount(rs3.getInt("count"));
                r.setBook(b);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,e.getMessage());
            frame.dispose();
        }
        String[][] rentsData = new String[rents.size()][9];

        for(int j=0;j<rents.size();j++){
            Rent r = rents.get(j);
            rentsData[j][0] = String.valueOf(r.getID());
            rentsData[j][1] = r.getUser().getFirstName() + " " + r.getUser().getLastName();
            rentsData[j][2] = r.getUser().getEmail();
            rentsData[j][3] = r.getUser().getPhoneNumber();
            rentsData[j][4] = String.valueOf(r.getBook().getID());
            rentsData[j][5] = r.getBook().getName()+" "+r.getBook().getAuthor();
            rentsData[j][6] = r.getBorrowTime();
            rentsData[j][7] = String.valueOf(r.getTotalDays());
            rentsData[j][8] = r.getStatusToString();
        }
        JScrollPane scrollPane = new JScrollPane(new JTable(rentsData, header, scheme2, scheme1));
        scrollPane.setBackground(null);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
