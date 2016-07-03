package registro.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DATConnection {
    
    private final String USER = "Jose-Laptop";
    private final String PASSWORD = "dbpassword";
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/biblioteca";
    
    public Connection connection;
    
    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, USER, PASSWORD);
    }
    
    public Connection openConection() throws ClassNotFoundException, SQLException {
        connection = createConnection();
        return connection;
    }
    
    public void closeConnection() throws SQLException {
        connection.close();
    }
    
    public Connection getConnection() {
        return connection;
    }
}
