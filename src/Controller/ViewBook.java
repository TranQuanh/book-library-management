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

        JPanel filterPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        filterPanel.setBackground(new Color(255, 208, 208));

        JTextField filterName = new JTextField();
        filterName.setToolTipText("Filter by Book Name");
        JTextField filterAuthor = new JTextField();
        filterAuthor.setToolTipText("Filter by Author");
        JTextField filterPublisher = new JTextField();
        filterPublisher.setToolTipText("Filter by Publisher");

        JButton filterButton = new JButton("Filter");
        filterButton.setBackground(new Color(168, 118, 118));
        filterButton.setForeground(Color.WHITE);

        filterPanel.add(new JLabel("Name:",22));
        filterPanel.add(filterName);
        filterPanel.add(new JLabel("Author:",22));
        filterPanel.add(filterAuthor);
        filterPanel.add(new JLabel("Publisher:",22));
        filterPanel.add(filterPublisher);
        frame.add(filterPanel, BorderLayout.SOUTH);



        String[] header = new String[]{
                "ID", "Title", "Author", "Publisher", "Count"
        };
        JScrollPane scrollPane = new JScrollPane();
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
                ArrayList<Book> books = new ArrayList<>();
                try{
                    ResultSet rs = database.getStatement().executeQuery(query.toString());
                    while (rs.next()){
                        Book book = new Book();
                        book.setID(rs.getInt("ID"));
                        book.setName(rs.getString("Name"));
                        book.setAuthor(rs.getString("Author"));
                        book.setPublisher(rs.getString("Publisher"));
                        book.setCount(rs.getInt("Count"));
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

                Color color2 = new Color(255, 208, 208);
                Color color1 = new Color(168, 118, 118);

                // Tạo JTable mới và cập nhật nội dung trong scrollPane
                JTable table = new JTable(booksData, header,color1,color2);
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

        JTable table = new JTable(booksData, header,color1,color2);
        scrollPane.setViewportView(table);
        scrollPane.setBackground(null);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        filterPanel.add(filterButton);
        frame.setVisible(true);
    }
}
