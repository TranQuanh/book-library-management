package Model;

import Controller.RentBook;
import Controller.ViewBook;

import java.util.Scanner;

public class Client extends User{
    private Operation[] operations = new Operation[]{
            new ViewBook(),
            new RentBook()};
    public Client() {
        super();
    }
    public void showList(Database database, Scanner sc){
        System.out.println("\n1. View Books");
        System.out.println("2. Rent Book");
        System.out.println("3. Return Book");
        System.out.println("4. Show My Rents");
        System.out.println("5. Edit My Data");
        System.out.println("6. Quit\n");
        int i = sc.nextInt();
        operations[i].operation(database,sc,this);
    }
}
