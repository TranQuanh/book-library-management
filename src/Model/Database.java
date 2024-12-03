package Model;

import java.sql.*;

public class Database {
    private String user ="root";
    private String password =  "111111";
    private String url = "jdbc:mysql://localhost:3306/booklibrary";
    Statement statement;

    public Database() {
        try {
            // Thêm dòng này để load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Thêm catch cho ClassNotFoundException
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }
    public Statement getStatement() {
        return statement;
    }
}
