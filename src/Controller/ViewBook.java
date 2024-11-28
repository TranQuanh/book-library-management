package Controller;

import Model.Book;
import Model.Database;
import Model.JLabel;
import Model.JTable;
import Model.Operation;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewBook implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("View Book");
        frame.setSize(1200,750);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(255, 208, 208));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("List of Books",45);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        title.setFont(new Font("Noto Serif", Font.BOLD, 45));
        title.setForeground(new Color(168, 118, 118));
        frame.add(title,BorderLayout.NORTH);

        String[] header = new String[]{
                "ID", "Title", "Author", "Publisher", "Count"
        };

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
            JOptionPane.showMessageDialog(frame,e.getMessage());
        }

        String[][] booksData = new String[books.size()][6];

        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
//            if(b.getCount()>0){
                booksData[i][0] = String.valueOf(b.getID());
                booksData[i][1] = b.getName();
                booksData[i][2] = b.getAuthor();
                booksData[i][3] = b.getPublisher();
                booksData[i][4] = String.valueOf(b.getCount());
//            }
        }

        Color color2 = new Color(255, 208, 208);
        Color color1 = new Color(168, 118, 118);

        JScrollPane scrollPane = new JScrollPane(new JTable(booksData, header, color1, color2));
        scrollPane.setBackground(null);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
