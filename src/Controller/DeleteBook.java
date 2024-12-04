package Controller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Model.*;
import Model.JButton;
import Model.JLabel;

import javax.swing.*;
import javax.swing.JTextField;

public class DeleteBook implements Operation {

    private JTextField name, author, publisher, number;
    private Database database;
    private JFrame frame;

    @Override
    public void operation(Database database, JFrame f, User user) {

        this.database = database;
        Color scheme1 = Color.white;
        Color scheme2 = new Color(101, 123, 119);

        frame = new JFrame("Delete Book");
        frame.setSize(800,800);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("Delete Book","Noto Serif Regular",45,scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,50,50));

        panel.add(new JLabel("ID: ", 22,scheme2));
        String[] ids= new String[] {" "};
        ArrayList<Integer> idsArray = new ArrayList<>();
        try{
            ResultSet rs1 = database.getStatement().executeQuery("SELECT `book_id` FROM `book` ");
            while (rs1.next()) {
                idsArray.add(rs1.getInt("book_id"));
            }
        } catch (Exception e0){
            System.out.println("loi o day");
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
        name.setEditable(false);
        panel.add(name);

        panel.add(new JLabel("Author:",22,scheme2));
        author = new Model.JTextField(22,Color.WHITE,scheme2);
        author.setEditable(false);
        panel.add(author);

        panel.add(new JLabel("Publisher:",22,scheme2));
        publisher = new Model.JTextField(22,Color.WHITE,scheme2);
        publisher.setEditable(false);
        panel.add(publisher);

        panel.add(new JLabel("Number of books:",22,scheme2));
        number = new Model.JTextField(22,Color.WHITE,scheme2);
        number.setEditable(false);
        panel.add(number);

        Model.JButton cancel = new JButton("Cancel",22,new Color(146, 171, 160),scheme2);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancel);

        JButton delete = new JButton("Delete",22,new Color(146, 171, 160),scheme2);
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
                        CreateBook createBook = new CreateBook();
                        String select = "SELECT count FROM `book` WHERE `book_id` = "+ id.getSelectedItem()+";";
                         ResultSet rs2 = database.getStatement().executeQuery(select);
                         rs2.next();
                         int book_count = rs2.getInt("count");


                        // Modified: Update count to 0 instead of deleting the book
                        String updateQuery = "UPDATE `book` SET `count` = 0 WHERE `book_id` = '"+id.getSelectedItem()+"';";
                        database.getStatement().executeUpdate(updateQuery);
                        JOptionPane.showMessageDialog(frame, "Book count set to 0 successfully");

                        String insert = "INSERT INTO `create_Book`(`book_id`,`admin_id`, " +
                                " `create_date`,`count`) VALUES " +
                                " ('"+id.getSelectedItem()+"', '"+user.getID()+"', '"+createBook.getCreateDate()+"'," +
                                "'"+-(book_count)+"');";
                        database.getStatement().executeUpdate(insert);

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
                        .executeQuery("SELECT * FROM `book` WHERE `book_id` = '" + ID+ "';");
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
