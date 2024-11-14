package Model;

public class Admin extends User {
    public Admin() {
        super();
    }
    @Override
    public void showList(){
        System.out.println("\n1. Add New Book");
        System.out.println("2. View Books");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Add New Admin");
        System.out.println("6. View Admin");
        System.out.println("7. Quit");
    }
}
