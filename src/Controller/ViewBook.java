package Controller;

import Model.Book;
import Model.Database;
import Model.JLabel;
import Model.JTable;
import Model.Operation;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewBook implements Operation {
    @Override
    public  void operation(Database database, JFrame f, User user) {
        Color scheme1 = new Color(255, 208, 208);
        Color scheme2 = new Color(168, 118, 118);

        JFrame frame = new JFrame("View Book");
        frame.setSize(1200,1000);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("List of Books","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title,BorderLayout.NORTH);

        JPanel filterPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
        filterPanel.setBackground(scheme1);

        JTextField filterName = new Model.JTextField(22,Color.WHITE,scheme2);;
        filterName.setToolTipText("Filter by Book Name");
        JTextField filterAuthor = new Model.JTextField(22,Color.WHITE,scheme2);;
        filterAuthor.setToolTipText("Filter by Author");
        JTextField filterPublisher = new Model.JTextField(22,Color.WHITE,scheme2);;
        filterPublisher.setToolTipText("Filter by Publisher");

        Model.JButton filterButton = new Model.JButton("Filter",22,new Color(233, 155, 155),scheme2);

        filterPanel.add(new JLabel("Name:",22,scheme2));
        filterPanel.add(filterName);
        filterPanel.add(new JLabel("Author:",22,scheme2));
        filterPanel.add(filterAuthor);
        filterPanel.add(new JLabel("Publisher:",22,scheme2));
        filterPanel.add(filterPublisher);
        frame.add(filterPanel, BorderLayout.SOUTH);



        String[] header = new String[]{
                "ID", "Title", "Author", "Publisher", "Count"
        };
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.add(scrollPane, BorderLayout.CENTER);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameFilter = filterName.getText().trim();
                String authorFilter = filterAuthor.getText().trim();
                String publisherFilter = filterPublisher.getText().trim();

                StringBuilder query = new StringBuilder("SELECT * FROM `book` WHERE 1=1 ");
                if (!nameFilter.isEmpty()) {
                    query.append("AND `Name` LIKE '%").append(nameFilter).append("%' ");
                }
                if (!authorFilter.isEmpty()) {
                    query.append("AND `Author` LIKE '%").append(authorFilter).append("%' ");
                }
                if (!publisherFilter.isEmpty()) {
                    query.append("AND `Publisher` LIKE '%").append(publisherFilter).append("%' ");
                }
                System.out.println(query);
                ArrayList<Book> books = new ArrayList<>();
                try{
                    ResultSet rs = database.getStatement().executeQuery(query.toString());
                    while (rs.next()){
                        Book book = new Book();
                        book.setID(rs.getInt("book_id"));
                        book.setName(rs.getString("name"));
                        book.setAuthor(rs.getString("author"));
                        book.setPublisher(rs.getString("publisher"));
                        book.setCount(rs.getInt("count"));
                        books.add(book);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
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

                // Tạo JTable mới và cập nhật nội dung trong scrollPane
                JTable table = new JTable(booksData, header,scheme2,scheme1);
                scrollPane.setViewportView(table); // Cập nhật bảng trong scrollPane
                scrollPane.revalidate(); // Làm mới giao diện
                scrollPane.repaint(); // Vẽ lại giao diện
            }
        });

        String select = "SELECT * FROM `book`;";
        ArrayList<Book> books = new ArrayList<>();
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()){
                Book book = new Book();
                book.setID(rs.getInt("book_id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setCount(rs.getInt("count"));
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

        JTable table = new JTable(booksData, header,scheme2,scheme1);
        scrollPane.setViewportView(table);
        scrollPane.setBackground(scheme1);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        filterPanel.add(filterButton);
        frame.setVisible(true);
    }
}
