package Controller;

import Model.*;
import Model.JButton;
import Model.JComboBox;
import Model.JLabel;
import Model.JTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowSpecUserRents implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user) {

        JFrame frame = new JFrame("Show User's Rents");
        frame.setSize(600,260);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(255, 208, 208));
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("List of Rents",45);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        title.setFont(new Font("Noto Serif", Font.BOLD, 45));
        title.setForeground(new Color(168, 118, 118));
        frame.add(title,BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("User ID:", 22));

        ArrayList<Integer> ids = new ArrayList<>();
        try{
            ResultSet rs0 = database.getStatement().executeQuery("SELECT `ID` FROM `user` WHERE `Type` = '0';");
            while(rs0.next()) {
                ids.add(rs0.getInt("ID"));


            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(frame,e.getMessage());
            frame.dispose();
        }

        String[] idsArray = new String[ids.size()+1];
        idsArray[0] = " ";
        for(int i=0;i<ids.size();i++){
            idsArray[i+1] = String.valueOf(ids.get(i));
        }
        JComboBox id = new JComboBox(idsArray,22);
        panel.add(id);

        JButton showUsers = new JButton("Show All Users", 22);
        showUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUsers(database,frame);
            }
        });
        panel.add(showUsers);

        JButton confirm = new JButton ("Confirm", 22);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(id.getSelectedItem().toString().equals(" ")) {
                    JOptionPane.showMessageDialog(frame, "User Id cannot be empty");
                    return;
                }
                new ShowUserRents(Integer.parseInt(id.getSelectedItem().toString())).operation(database, frame, user);
                frame.dispose();
            }
        });
        panel.add(confirm);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.requestFocus();



    }
    private void showUsers(Database database,JFrame frame){
        JFrame frame2 = new JFrame("Client list");
        frame2.setSize(1000,600);
        frame2.setLocationRelativeTo(frame);
        frame2.getContentPane().setBackground(new Color(255, 208, 208));
        frame2.setLayout(new BorderLayout());

        JLabel title = new JLabel("Clients",45);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        title.setFont(new Font("Noto Serif", Font.BOLD, 45));
        title.setForeground(new Color(168, 118, 118));
        frame2.add(title,BorderLayout.NORTH);

        String[] header = new String[]{"ID","First Name","Last Name","Email","Tel"};

        ArrayList<User> users = new ArrayList<>();

        try{
            ResultSet rs = database.getStatement().executeQuery("SELECT * FROM `user`;");
            while(rs.next()) {
                int accType = rs.getInt("type");
                if(accType == 0) {
                    User u = new Client();
                    u.setID(rs.getInt("ID"));
                    u.setFirstName(rs.getString("firstname"));
                    u.setLastName(rs.getString("lastname"));
                    u.setEmail(rs.getString("email"));
                    u.setPhoneNumber(rs.getString("phonenumber"));
                    users.add(u);
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(frame,e.getMessage());
            frame.dispose();
        }

        String[][] usersData = new String[users.size()][5];
        for(int i=0;i<users.size();i++){
            usersData[i][0] = String.valueOf(users.get(i).getID());
            usersData[i][1] = users.get(i).getFirstName();
            usersData[i][2] = users.get(i).getLastName();
            usersData[i][3] = users.get(i).getEmail();
            usersData[i][4] = users.get(i).getPhoneNumber();
        }
        Color color2 = new Color(255, 208, 208);
        Color color1 = new Color(168, 118, 118);

        JScrollPane panel = new JScrollPane(new JScrollPane(new JTable(usersData,header,color1,color2)));
        panel.setBackground(null);
        panel.getViewport().setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        frame2.add(panel,BorderLayout.CENTER);
        frame2.setVisible(true);
    }
}
