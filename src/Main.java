import Model.Admin;
import Model.Client;
import Model.Database;
import Model.User;

import java.util.Scanner;

import static Model.Admin.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       Scanner sc =new Scanner(System.in);
       Database db = new Database();
        Client ad = new Client();
        ad.showList(db,sc);
    }
}