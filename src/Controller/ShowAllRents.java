package Controller;
import Model.Database;
import Model.JTable;
import Model.Operation;
import Model.Rent;
import Model.User;
import Model.Client;
import Model.Book;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;
import java.util.jar.JarEntry;

import Model.JButton;
import Model.JLabel;
import Model.JTextField;

public class ShowAllRents implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user) {

        Color scheme1 = new Color(255, 208, 208);
        Color scheme2 = new Color(168, 118, 118);

        JFrame frame = new JFrame();
        frame.setSize(1500,750);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("List of Rents","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title,BorderLayout.NORTH);

        String[] header = new String[] {
                "ID","Name","Email","Phone Number","Book ID","Book",
                "Date time","Total days","Status"
        };

        ArrayList<Rent> rents = new ArrayList<>();
        ArrayList<Integer> bookIDs = new ArrayList<>();
        ArrayList<Integer> userIDs = new ArrayList<>();

        try{
            String select = "SELECT * FROM `rent` ;";
            ResultSet rs = database.getStatement().executeQuery(select);
            while(rs.next()){
                Rent rent = new Rent();
                rent.setID(rs.getInt("id"));
                userIDs.add(rs.getInt("userid"));
                bookIDs.add(rs.getInt("bookid"));
                rent.setBorrowTime(rs.getString("borrowtime"));
                rent.setTotalDays(rs.getInt("totaldays"));
                rent.setStatus(rs.getInt("status"));
                rents.add(rent);
            }

            for(int j = 0; j < rents.size(); j++){
                Rent r = rents.get(j);

                String selectUser = "SELECT * FROM `user` WHERE `ID` = '"+userIDs.get(j)+"';";
                ResultSet rs2 = database.getStatement().executeQuery(selectUser);
                rs2.next();
                User u = new Client();
                u.setID(rs2.getInt("ID"));
                u.setFirstName(rs2.getString("firstname"));
                u.setLastName(rs2.getString("lastname"));
                u.setEmail(rs2.getString("email"));
                u.setPhoneNumber(rs2.getString("phonenumber"));
                u.setPassword(rs2.getString("password"));
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

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,e.getMessage());
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
        scrollPane.setBackground(scheme1);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
