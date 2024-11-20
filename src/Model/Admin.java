package Model;

import Controller.AddNewAccount;
import Controller.AddNewBook;

import java.util.Scanner;

public class Admin extends User {
    private Operation[] operations = new Operation[]{new AddNewBook(),new AddNewAccount(1)};
    public Admin() {
        super();
    }
    @Override
    public void showList(Database database, Scanner sc) {
        System.out.println("\n1. Add New Book");
        System.out.println("2. View Books");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Add New Admin");
        System.out.println("6. View Admin");
        System.out.println("7. Quit\n");

        int i = sc.nextInt();
        operations[i].operation(database,sc,this);
        showList(database,sc);
    }
}
