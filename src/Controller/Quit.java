package Controller;

import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

import javax.swing.*;

public class Quit implements Operation {
    @Override
    public void operation(Database database, JFrame f, User user) {
        System.out.println("Thanks for visiting us!");

    }
}

