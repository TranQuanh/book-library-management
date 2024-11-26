package Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.*;
import java.sql.SQLException;
import Model.Database;
import Model.JButton;
import Model.JLabel;
import Model.JTextField;
import Model.Operation;
import Model.User;

import javax.swing.*;

public class AddNewBook implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user){
        JFrame frame = new JFrame("Add New Book");
        frame.setSize(600,525);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(27, 150, 250));
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("Add New Book",35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Name:",22));
        JTextField name = new JTextField(22);
        panel.add(name);

        panel.add(new JLabel("Author:",22));
        JTextField author = new JTextField(22);
        panel.add(author);

        panel.add(new JLabel("Publisher:",22));
        JTextField publisher = new JTextField(22);
        panel.add(publisher);

        panel.add(new JLabel("Number of books:",22));
        JTextField number = new JTextField(22);
        panel.add(number);

        JButton cancel = new JButton("Cancel",22);
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

                try {
                    ResultSet rs = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM `book`");
                    rs.next();
                    int ID = rs.getInt("count") + 1;
                    String insert = "INSERT INTO `book`(`id`,`name`,`author`, " +
                            " `publisher`,`count`) VALUES " +
                            " ('"+ID+"', '"+name.getText()+"', '"+author.getText()+"', '"+publisher.getText()+"'," +
                            "'"+num+"');";
                    database.getStatement().executeUpdate(insert);
                    JOptionPane.showMessageDialog(frame, "Book Added");
                    frame.dispose();
                }
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel.add(save);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
