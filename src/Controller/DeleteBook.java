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

public class DeleteBook implements Operation {

    private JTextField name, author, publisher, number;
    private Database database;
    private JFrame frame;

    @Override
    public void operation(Database database, JFrame f, User user) {

        this.database = database;

        frame = new JFrame("Delete Book");
        frame.setSize(600,600);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(27, 150, 250));
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("Delete Book",35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("ID: ", 22));
        String[] ids= new String[] {" "};
        ArrayList<Integer> idsArray = new ArrayList<>();
        try{
            ResultSet rs1 = database.getStatement().executeQuery("SELECT `id` FROM `book` ");
            while (rs1.next()) {
                idsArray.add(rs1.getInt("ID"));
            }
        } catch (Exception e0){
            System.out.println("0202");
            JOptionPane.showMessageDialog(frame, e0.getMessage());
            frame.dispose();
        }

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
        name.setEditable(false);
        panel.add(name);

        panel.add(new JLabel("Author:",22));
        author = new Model.JTextField(22);
        author.setEditable(false);
        panel.add(author);

        panel.add(new JLabel("Publisher:",22));
        publisher = new Model.JTextField(22);
        publisher.setEditable(false);
        panel.add(publisher);

        panel.add(new JLabel("Number of books:",22));
        number = new Model.JTextField(22);
        number.setEditable(false);
        panel.add(number);

        Model.JButton cancel = new JButton("Cancel",22);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        JButton delete = new JButton("Delete",22);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate inputs
                if (id.getSelectedItem().equals(" ")) {
                    JOptionPane.showMessageDialog(frame, "Please select a book ID");
                    return;
                }

                int confirmDialog = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this book?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (confirmDialog == JOptionPane.YES_OPTION) {
                    try {
                        // Modified: Update count to 0 instead of deleting the book
                        String updateQuery = "UPDATE `book` SET `count` = 0 WHERE `ID` = '"+id.getSelectedItem()+"';";
                        database.getStatement().executeUpdate(updateQuery);
                        JOptionPane.showMessageDialog(frame, "Book count set to 0 successfully");
                        frame.dispose();
                    } catch (SQLException e2) {
                        System.out.println("Delete error");
                        JOptionPane.showMessageDialog(frame, e2.getMessage());
                    }
                }
            }
        });
        panel.add(delete);

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
                System.out.println("345");
                JOptionPane.showMessageDialog(frame, e1.getMessage());
                frame.dispose();
            }
        }
    }
}
