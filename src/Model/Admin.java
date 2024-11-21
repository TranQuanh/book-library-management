package Model;

import Controller.*;

import java.util.Scanner;

public class Admin extends User {
    private Operation[] operations = new Operation[]{new AddNewBook(),
            new ViewBook(),
            new UpdateBook(),
            new DeleteBook(),
            new AddNewAccount(1),
            new Quit()
    };
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
//        System.out.println("6. Show Rent's");
//        System.out.println("7. Show User's Rents");
        System.out.println("8. Edit my Data");
        System.out.println("9. Change Password");
        System.out.println("10. Quit\n");

        int i = sc.nextInt();
        if(i<1 || i>7){
            showList(database, sc);
            return;
        }
        operations[i-1].operation(database,sc,this);
        showList(database,sc);
    }
}
