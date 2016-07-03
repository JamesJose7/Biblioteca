package registro.controller;

import java.sql.SQLException;
import registro.model.DATConnection;

public class DBConnection {
    
    DATConnection connectionManager  = new DATConnection();
    
    public void openConection() throws ClassNotFoundException, SQLException {
        connectionManager.openConection();
    }
    
    public void closeConection() throws SQLException {
        connectionManager.closeConnection();
    }
    
    public DATConnection getDATConnection() {
        return connectionManager;
    }
}
