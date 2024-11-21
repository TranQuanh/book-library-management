package Controller;


import Model.Database;
import Model.Operation;
import Model.User;

import java.util.Scanner;

public class Quit implements Operation {

    @Override
    public void operation(Database database, Scanner sc, User user) {
        System.out.println("Thanks for visiting us !");
        sc.close();
    }
}
