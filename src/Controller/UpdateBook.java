package Controller;
import java.util.*;

import Model.Database;
import Model.Operation;
import Model.User;

public class UpdateBook implements Operation {

    @Override
    public void operation(Database database, Scanner sc, User user) {
        System.out.println("Enter book ID(int):(-1 to show all books)");
        int ID = sc.nextInt();
        String update = "UPDATE cars SET Name = '[value-2]', Author = '[value-3]', Publisher = '[value-4]', Count = '[value-5]', Price = '[value-6]', Available = '[value-7]' WHERE ID = '';";
    }
}
