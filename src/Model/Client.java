package Model;

import Controller.*;

import java.util.Scanner;

public class Client extends User{
    private Operation[] operations = new Operation[]{
            new ViewBook(),
            new RentBook(),
            new ReturnBook(),
            new ShowUserRents(-99999),
            new EditUserData()
    };
    public Client() {
        super();
    }
    public void showList(Database database, Scanner sc){
        System.out.println("\n1. View Books");
        System.out.println("2. Rent Book");
        System.out.println("3. Return Book");
        System.out.println("4. Show My Rents");
        System.out.println("5. Edit My Data");
        System.out.println("6. Change Password");
        System.out.println("7. Quit\n");
        int i = sc.nextInt();
        operations[i-1].operation(database,sc,this);
        showList(database,sc);
    }
}
