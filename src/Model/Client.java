package Model;

import Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Client extends User{
    private Operation[] operations = new Operation[]{
            new ViewBook(),
            new RentBook(),
            new ReturnBook(),
            new ShowUserRents(-9999),
            new EditUserData(),
            new ChangePassword(),
//            new Quit()
    };
    private JButton[] btns = new JButton[]{
            new JButton("View Books", 22),
            new JButton("Rent Book",22),
            new JButton("Return Book",22),
            new JButton("Show My Rents", 22),
            new JButton("Edit My Data", 22),
            new JButton("Change Password", 22)
    };
    public Client() {
        super();
    }
    public void showList(Database database, JFrame f) {
        JFrame frame = new JFrame("Client Panel");
        frame.setSize(400,btns.length*90);
        frame.setLocationRelativeTo(f);
        frame.getContentPane().setBackground(new Color(250,206,27));
        frame.setLayout(new BorderLayout());
        JLabel title = new JLabel("Welcome User: "+getFirstName(),30);
        title.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(btns.length,1,15,15));
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        for(int i=0;i<btns.length;i++) {
            final int j = i;
            JButton button = btns[i];
            panel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    operations[j].operation(database,frame,Client.this);

                }
            });
        }
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
