//package Controller;
//
//import Model.Database;
//import Model.Operation;
//import Model.User;
//
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class DeleteBook implements Operation {
//    @Override
//    public void operation(Database database, Scanner sc, User user) {
//        System.out.println("Enter ID (int): (-1 to show all cars)");
//        int id =sc.nextInt();
//        while(id ==-1){
//            new ViewBook().operation(database, sc, user);
//            System.out.println("Enter ID (int): (-1 to show all cars)");
//            id =sc.nextInt();
//        }
//        try{
//            String update = "UPDATE `book` SET `count` = '0' WHERE `id` =  '"+id+"';";
//            database.getStatement().execute(update);
//            System.out.println("Book deleted successfully");
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
//}
