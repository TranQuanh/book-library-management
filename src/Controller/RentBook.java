package Controller;

import Model.*;
import Model.JButton;
import Model.JLabel;

import javax.swing.*;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RentBook implements Operation {

    private JTextField name, author, publisher, number;
    private Database database;
    private JFrame frame;

    public void operation(Database database, JFrame f, User user){

        this.database = database;

        frame = new JFrame("Rent Book");
        frame.setSize(600,650);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(27, 150, 250));
        frame.setLayout(new BorderLayout());

        Model.JLabel title = new JLabel("rent Book",35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(8,2,15,15));
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

        panel.add(new JLabel("Days:",22));
        JTextField days = new Model.JTextField(22);
        panel.add(days);

//        JButton showBooks = new JButton("",22);
        panel.add(new JLabel("",22));
//        panel.add(showBooks);

        JButton confirm = new JButton("Confirm",22);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getSelectedItem().toString().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please select book ID");
                    return;
                }
                if(days.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please select day");
                    return;
                }
                int daysInt;
                try{
                    daysInt = Integer.parseInt(days.getText());
                }catch(Exception e5){
                    JOptionPane.showMessageDialog(frame, "Days must be int");
                    return;
                }
                try{

                    ResultSet rs0 = database.getStatement()
                            .executeQuery("SELECT * FROM `book` WHERE `ID` = '"+id.getSelectedItem().toString()+"';");
                    rs0.next();
                    Book book = new Book();
                    book.setID(rs0.getInt("ID"));
                    book.setName(rs0.getString("name"));
                    book.setAuthor(rs0.getString("author"));
                    book.setPublisher(rs0.getString("publisher"));
                    book.setCount(rs0.getInt("count"));

                    if(book.getCount() <= 0) {
                        JOptionPane.showMessageDialog(frame, "Count is 0 ");
                        return;
                    }

                    ResultSet rs1 = database.getStatement().executeQuery("SELECT COUNT(*) as count FROM `rent`;");
                    rs1.next();
                    int ID = rs1.getInt("count") + 1;

                    Rent rent = new Rent();

                    String insert = "INSERT INTO `rent` (`id`,`userid`,`bookid`,`borrowtime`,`totaldays`,`Status`)" +
                            " VALUES('"+ID+"','"+user.getID()+"','"+book.getID()+"','"+rent.getBorrowTime()+"','"+daysInt+"','"+0+"')";
                    database.getStatement().execute(insert);
                    JOptionPane.showMessageDialog(frame, "Book Rent Successfully");
                    frame.dispose();
                } catch(SQLException exception){
                    JOptionPane.showMessageDialog(frame, exception.getMessage());
                }
            }
        });
        panel.add(confirm);
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
