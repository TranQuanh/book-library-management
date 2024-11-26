//package Controller;
//
//import Model.Database;
//import Model.Operation;
//import Model.User;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class ChangePassword implements Operation {
//    @Override
//    public void operation(Database database, Scanner sc, User user) {
//        System.out.println("Enter old password:");
//        String oldPassword = sc.next();
//        if(!oldPassword.equals(user.getPassword())) {
//            System.out.println("Password does not match!");
//            return;
//        }
//
//        System.out.println("Enter new password:");
//        String newPassword = sc.next();
//        System.out.println("Confirm password:");
//        String confirmPassword = sc.next();
//        while(!newPassword.equals(confirmPassword)) {
//            System.out.println("Password does not match!");
//            System.out.println("Enter new password:");
//            newPassword = sc.next();
//            System.out.println("Confirm password:");
//            confirmPassword = sc.next();
//        }
//
//        try{
//            String update = "UPDATE `user` SET" +
//                    " `password`='"+newPassword+"' WHERE `ID` = '"+user.getID()+"'";
//            database.getStatement().execute(update);
//            System.out.println("Password changed successfully!");
//            user.setPassword(newPassword);
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
//}
