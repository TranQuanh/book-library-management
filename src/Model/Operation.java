package Model;
import Model.Database;
import Model.User;
import java.util.Scanner;

public interface Operation {
    public void operation(Database database, Scanner sc,User user);

}
