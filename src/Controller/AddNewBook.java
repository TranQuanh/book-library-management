package Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.*;
import java.sql.SQLException;

import Model.*;
import Model.JButton;
import Model.JLabel;
import Model.JTextField;

import javax.swing.*;

public class AddNewBook implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user){
        Color scheme1 = new Color(255, 208, 208);
        Color scheme2 = new Color(168, 118, 118);

        JFrame frame = new JFrame("Add New Book");
        frame.setSize(700,650);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("Add New Book","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

        panel.add(new JLabel("Name:",22,scheme2));
        JTextField name = new JTextField(22);
        panel.add(name);

        panel.add(new JLabel("Author:",22,scheme2));
        JTextField author = new JTextField(22);
        panel.add(author);

        panel.add(new JLabel("Publisher:",22,scheme2));
        JTextField publisher = new JTextField(22);
        panel.add(publisher);

        panel.add(new JLabel("Number of books:",22,scheme2));
        JTextField number = new JTextField(22);
        panel.add(number);

        JButton cancel = new JButton("Cancel",22,new Color(233, 155, 155),scheme2);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }

        });
        panel.add(cancel);

        JButton save = new JButton("Save",22,new Color(233, 155, 155),scheme2);
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

                try {

                    String checkQuery = "SELECT book_id, count FROM `Book` WHERE name = '" + name.getText() +
                            "' AND author = '" + author.getText() +
                            "' AND publisher = '" + publisher.getText() + "'";
                    ResultSet rs = database.getStatement().executeQuery(checkQuery);

                    if (rs.next()) {

                        int existingCount = rs.getInt("count");
                        int newCount = existingCount + num;
                        int existingId = rs.getInt("book_id");

                        String updateQuery = "UPDATE `book` SET count = " + newCount +
                                " WHERE id = " + existingId;
                        database.getStatement().executeUpdate(updateQuery);

                        JOptionPane.showMessageDialog(frame, "Book Updated!");
                    } else {

                        String insert = "INSERT INTO `book`(`name`,`author`, " +
                                " `publisher`,`count`) VALUES " +
                                " ('"+name.getText()+"', '"+author.getText()+"', '"+publisher.getText()+"'," +
                                "'"+num+"');";
                        database.getStatement().executeUpdate(insert);
                        JOptionPane.showMessageDialog(frame, "Book Added");
                    }

                }
                catch (SQLException ex) {
                    System.out.println("54312");
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }

                try {
                    ResultSet rs2 = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM `book`");
                    rs2.next();
                    int idBook = rs2.getInt("count") ;
                    CreateBook createBook = new CreateBook();

                    String insert = "INSERT INTO `create_book`(`book_id`,`admin_id`, " +
                            " `create_date`,`count`) VALUES " +
                            " ('"+idBook+"', '"+user.getID()+"', '"+createBook.getCreateDate()+"'," +
                            "'"+num+"');";
                    database.getStatement().executeUpdate(insert);


                    frame.dispose();
                }
                catch (SQLException exq) {
                    System.out.println("123");
                    JOptionPane.showMessageDialog(frame, exq.getMessage());
                }

            }
        });
        panel.add(save);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
