package Controller;

import Model.*;
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

import Model.JButton;
import Model.JLabel;
import Model.JTextField;
public class ReturnBook implements Operation {
    public void operation(Database database, JFrame f, User user) {
        Color scheme1 = Color.WHITE;
        Color scheme2 = new Color(101, 123, 119);

        JFrame frame = new JFrame("Return Book");
        frame.setSize(600,400);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(scheme1);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Return Book","Noto Serif Regular",45);
        title.setForeground(scheme2);
        title.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        panel.add(new JLabel("Rent ID: ","Inter 24pt Regular", 22,scheme2));

        String[] ids= new String[] {" "};
        ArrayList<Integer> idsArray = new ArrayList<>();
        try{
            ResultSet rs1 = database.getStatement().executeQuery("SELECT `rent_id` FROM `rent` WHERE `client_id` = '"+user.getID()+"' AND status = 0" );
            while (rs1.next()) {
                idsArray.add(rs1.getInt("rent_id"));
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

        Model.JComboBox id = new Model.JComboBox(ids,22,scheme2);
        panel.add(id);

        JButton showRents = new JButton("Show my Rents",22,new Color(146, 171, 160),scheme2);
        showRents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowUserRents(user.getID()).operation(database, frame, user);
            }
        });
        panel.add(showRents);

//        JButton confirm = new JButton("Confirm",22);
        JButton confirm = new JButton("Confirm",22,new Color(146, 171, 160),scheme2);
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getSelectedItem().toString().equals(" ")){
                    JOptionPane.showMessageDialog(frame, "Rent ID cannot be empty!");
                    return;
                }

                try{
                    String select = "SELECT * FROM `rent` WHERE `rent_id` = '"+id.getSelectedItem().toString()+"';";
                    ResultSet rs = database.getStatement().executeQuery(select);
                    rs.next();
                    Rent r = new Rent();
                    r.setID(rs.getInt("rent_id"));
                    r.setUser(user);
                    r.setBorrowTime(rs.getString("rent_date"));
                    r.setTotalDays(rs.getInt("total_day"));
                    r.setStatus(rs.getInt("status"));

                    if(r.getStatusToString().equals("Delayed")){
                        JOptionPane.showMessageDialog(frame, r.getDelayedDays()+"delayed days\n" +
                                "You delayed");
                    }
                    String update = "UPDATE `rent` SET `status`='1' WHERE `rent_id` = '" + id.getSelectedItem().toString() + "';";
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
