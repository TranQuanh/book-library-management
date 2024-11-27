package Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Model.Book;
import Model.Database;
import Model.JButton;
import Model.JLabel;
import Model.Operation;
import Model.User;

import javax.swing.*;

public class UpdateBook implements Operation {

    private JTextField name, author, publisher, number;
    private Database database;
    private JFrame frame;

    @Override
    public void operation(Database database, JFrame f, User user) {

        this.database = database;

        frame = new JFrame("Update Book");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(27, 150, 250));
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("Update Book",35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("ID: ", 22));
        String[] ids= new String[] {" "};
        ArrayList<Integer> idsArray = new ArrayList<>();
        try{
            ResultSet rs1 = database.getStatement().executeQuery("SELECT 'ID' FROM `book` ");
            while (rs1.next()) {
                idsArray.add(rs1.getInt("ID"));
            }
        } catch (Exception e0){
            JOptionPane.showMessageDialog(frame, e0.getMessage());
            frame.dispose();
        };

        ids = new String[idsArray.size() + 1];
        ids[0] = " ";
        for (int i = 1; i<= idsArray.size(); i++) {
            ids[i] = String.valueOf(idsArray.get(i-1));
        }

        Model.JComboBox id = new Model.JComboBox(ids, 22);
        id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData(id.getSelectedItem().toString());
            }
        });
        panel.add(id);

        panel.add(new JLabel("Name:",22));
        name = new Model.JTextField(22);
        panel.add(name);

        panel.add(new JLabel("Author:",22));
        author = new Model.JTextField(22);
        panel.add(author);

        panel.add(new JLabel("Publisher:",22));
        publisher = new Model.JTextField(22);
        panel.add(publisher);

        panel.add(new JLabel("Number of books:",22));
        number = new Model.JTextField(22);
        panel.add(number);

        Model.JButton cancel = new JButton("Cancel",22);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }

        });
        panel.add(cancel);

        JButton save = new JButton("Save",22);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (name.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a title");
                }
                if (author.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a author");
                }
                if (publisher.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a publisher");
                }
                if (number.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a number");
                }
                int num ;
                try {
                    num = Integer.parseInt(number.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a number");
                    return;
                }
                try{
                String update = "UPDATE `book` SET `name` = '"+name.getText()+"', `author` = '"+author.getText()+"', " +
                    "`publisher` = '"+publisher.getText()+ "'WHERE `ID` = '"+id.getSelectedItem()+"';";
                database.getStatement().executeUpdate(update);
                JOptionPane.showMessageDialog(frame, "Book updated successfully");
                frame.dispose();
                } catch (SQLException e2) {
                    System.out.println("1234");
                    JOptionPane.showMessageDialog(frame, e2.getMessage());
                }
            }
        });
        panel.add(save);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.requestFocus();


//        System.out.println("Enter book ID(int):(-1 to show all books)");
//        int ID = sc.nextInt();
//        while(ID == -1){
//            new ViewBook().operation(database, sc, user);
//            System.out.println("Enter book ID(int):(-1 to show all books)");
//            ID = sc.nextInt();
//        }
//        try{
//            ResultSet rs1 = database.getStatement()
//                    .executeQuery("SELECT * FROM `book` WHERE `ID` = '"+ID+"';");
//            rs1.next();
//            Book book = new Book();
//            book.setID(rs1.getInt("ID"));
//            book.setName(rs1.getString("name"));
//            book.setAuthor(rs1.getString("author"));
//            book.setPublisher(rs1.getString("publisher"));
//            book.setCount(rs1.getInt("count"));
//
//            if(book.getCount() == 0) {
//                System.out.println("Book does not exist");
//                return;
//            }
//
//            System.out.println("Enter Name: (-1: "+book.getName()+")");
//            String name = sc.next();
//            if(name.equals("-1")) name = book.getName();
//
//            System.out.println("Enter Author: (-1: "+book.getAuthor()+")");
//            String author = sc.next();
//            if(author.equals("-1")) author = book.getAuthor();
//
//            System.out.println("Enter Publisher: (-1: "+book.getPublisher()+")");
//            String publisher = sc.next();
//            if(publisher.equals("-1")) publisher = book.getPublisher();
//
//            String update = "UPDATE `book` SET `name` = '"+name+"', `author` = '"+author+"', " +
//                    "`publisher` = '"+publisher + "'WHERE `ID` = '"+ID+"';";
//
//            database.getStatement().executeUpdate(update);
//            System.out.println("Book updated successfully");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
    private void updateData(String ID) {
        if (ID.equals(" ")) {
            name.setText("");
            author.setText("");
            publisher.setText("");
        }
        else{
            try {
                ResultSet rs1 = database.getStatement()
                        .executeQuery("SELECT * FROM `book` WHERE `ID` = '" + ID+ "';");
                rs1.next();
                Book book = new Book();
                book.setID(rs1.getInt("ID"));
                name.setText(rs1.getString("name"));
                author.setText(rs1.getString("author"));
                publisher.setText(rs1.getString("publisher"));
            }catch(Exception e1){
                System.out.println("345");
                JOptionPane.showMessageDialog(frame, e1.getMessage());
                frame.dispose();
            }
        }
    }
}
