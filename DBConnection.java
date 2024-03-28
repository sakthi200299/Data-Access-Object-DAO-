import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    Connection connection=null;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Instead of just printing the stack trace, handle the exception appropriately
            System.err.println("Failed to establish connection to the database:");
            e.printStackTrace();
            // You might want to throw a custom exception or log the error here
        }
    }
      public Connection getConnection() {
        return connection;
    }
    

   
     
}
