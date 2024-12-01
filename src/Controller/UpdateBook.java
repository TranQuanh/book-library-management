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
        Color scheme1 = new Color(255, 208, 208);
        Color scheme2 = new Color(168, 118, 118);

        frame = new JFrame("Update Book");
        frame.setSize(800,800);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("Update Book","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

        panel.add(new JLabel("ID: ", 22,scheme2));
        String[] ids= new String[] {" "};
        ArrayList<Integer> idsArray = new ArrayList<>();
        try{
            ResultSet rs1 = database.getStatement().executeQuery("SELECT `id` FROM `book` ");
            while (rs1.next()) {
                idsArray.add(rs1.getInt("ID"));
            }
        } catch (Exception e0){
            JOptionPane.showMessageDialog(frame, e0.getMessage());
            frame.dispose();
        }

        ids = new String[idsArray.size() + 1];
        ids[0] = " ";
        for (int i = 1; i<= idsArray.size(); i++) {
            ids[i] = String.valueOf(idsArray.get(i-1));
        }

        Model.JComboBox id = new Model.JComboBox(ids, 22,scheme2);
        id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateData(id.getSelectedItem().toString());
            }
        });
        panel.add(id);

        panel.add(new JLabel("Name:",22,scheme2));
        name = new Model.JTextField(22,Color.WHITE,scheme2);
        panel.add(name);

        panel.add(new JLabel("Author:",22,scheme2));
        author = new Model.JTextField(22,Color.WHITE,scheme2);
        panel.add(author);

        panel.add(new JLabel("Publisher:",22,scheme2));
        publisher = new Model.JTextField(22,Color.WHITE,scheme2);
        panel.add(publisher);

        panel.add(new JLabel("Number of books:",22,scheme2));
        number = new Model.JTextField(22,Color.WHITE,scheme2);
        panel.add(number);

        Model.JButton cancel = new JButton("Cancel",22,new Color(233, 155, 155),scheme2);
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
                // Validate inputs
                if (id.getSelectedItem().equals(" ")) {
                    JOptionPane.showMessageDialog(frame, "Please select a book ID");
                    return;
                }

                if (name.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a title");
                    return;
                }

                if (author.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter an author");
                    return;
                }

                if (publisher.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a publisher");
                    return;
                }

                if (number.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a number of books");
                    return;
                }

                int num;
                try {
                    num = Integer.parseInt(number.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number");
                    return;
                }

                try {
                    String update = "UPDATE `book` SET `name` = '"+name.getText()+"', " +
                            "`author` = '"+author.getText()+"', " +
                            "`publisher` = '"+publisher.getText()+"', " +
                            "`count` = '"+num+"' " +
                            "WHERE `ID` = '"+id.getSelectedItem()+"';";
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
    }

    private void updateData(String ID) {
        if (ID.equals(" ")) {
            name.setText("");
            author.setText("");
            publisher.setText("");
            number.setText("");
        }
        else {
            try {
                ResultSet rs1 = database.getStatement()
                        .executeQuery("SELECT * FROM `book` WHERE `ID` = '" + ID+ "';");
                rs1.next();
                name.setText(rs1.getString("name"));
                author.setText(rs1.getString("author"));
                publisher.setText(rs1.getString("publisher"));
                number.setText(String.valueOf(rs1.getInt("count")));
            } catch(Exception e1) {
                JOptionPane.showMessageDialog(frame, e1.getMessage());
                frame.dispose();
            }
        }
    }
}