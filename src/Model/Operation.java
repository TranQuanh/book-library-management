package Model;
import Model.Database;
import Model.User;

import javax.swing.*;
import java.util.Scanner;

public interface Operation {
    public void operation(Database database, JFrame f, User user);
}
