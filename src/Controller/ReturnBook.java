package Controller;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;
import java.util.jar.JarEntry;

public class ReturnBook implements Operation {
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("Return Book");
        frame.setSize(600,260);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250, 206, 27));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Rent Car", 35);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Rent ID: ",22));

        String[] ids= new String[] {" "};
        ArrayList<Integer> idsArray = new ArrayList<>();
        try{
            ResultSet rs1 = database.getStatement().executeQuery("SELECT `id` FROM `book` WHERE `user` = '"+user.getID()+"'");
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

//        JComboBox id = new JComboBox(ids,22);
        JComboBox id = new JComboBox(ids);
        panel.add(id);

//      JButton showRents = new JButton("Show my Rents",22);
        JButton showRents = new JButton("Show my Rents");
        showRents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowUserRents(user.getID()).operation(database, frame, user);
            }
        });
        panel.add(showRents);

//        JButton confirm = new JButton("Confirm",22);
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getSelectedItem().toString().equals(" ")){
                    JOptionPane.showMessageDialog(frame, "Rent ID cannot be empty!");
                    return;
                }

                try{
                    String select = "SELECT * FROM `rent` WHERE `ID` = '"+id.getSelectedItem().toString()+"';";
                    ResultSet rs = database.getStatement().executeQuery(select);
                    rs.next();
                    Rent r = new Rent();
                    r.setID(rs.getInt("id"));
                    r.setUser(user);
                    r.setBorrowTime(rs.getString("borrowtime"));
                    r.setTotalDays(rs.getInt("totaldays"));
                    r.setStatus(rs.getInt("status"));

                    if(r.getStatusToString().equals("Delayed")){
                        JOptionPane.showMessageDialog(frame, r.getDelayedDays()+"delayed days\n" +
                                "You delayed");
                    }
                    String update = "UPDATE `rent` SET `status`='1' WHERE `id` = '" + id.getSelectedItem().toString() + "';";
                    database.getStatement().execute(update);
                    JOptionPane.showMessageDialog(frame, "Book returned successfully");
                    frame.dispose();
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(frame, exception.getMessage());
                }
            }
        });
        panel.add(confirm);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.requestFocus();

    }
}
