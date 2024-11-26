//package Controller;
//import Model.Database;
//import Model.Operation;
//import Model.Rent;
//import Model.User;
//import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.util.*;
//public class ReturnBook implements Operation {
//    public void operation(Database database, Scanner sc, User user) {
//        System.out.println("Enter Rent ID(int): (-1 to show all rents)");
//        int ID = sc.nextInt();
//        while(ID==-1){
//            new ShowUserRents(user.getID()).operation(database, sc, user);
//            System.out.println("Enter Rent ID(int): (-1 to show all rents)");
//            ID = sc.nextInt();
//        }
//        try{
//            String select = "SELECT * FROM `rent` WHERE `ID` = '"+ID+"';";
//            ResultSet rs = database.getStatement().executeQuery(select);
//            rs.next();
//            Rent r = new Rent();
//            r.setID(rs.getInt("id"));
//            r.setUser(user);
//            r.setBorrowTime(rs.getString("borrowtime"));
//            r.setTotalDays(rs.getInt("totaldays"));
//            r.setStatus(rs.getInt("status"));
//
//            if(r.getStatusToString().equals("Delayed")){
//                System.out.println(r.getDelayedDays()+"delayed days");
//                System.out.println("You delayed");
//            }
//            String update = "UPDATE `rent` SET `status`='1' WHERE `id` = '" + ID + "';";
//            database.getStatement().execute(update);
//            System.out.println("Book returned successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
//}
