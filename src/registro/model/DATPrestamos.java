package registro.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jose
 */
public class DATPrestamos {
    private DATConnection mConnection = new DATConnection();
    
    private final String[] colNames = {
        "id_Prestamo", "id_Personas", "Fecha_Prestamo", "Fecha_Devolucion", "Estatus", "Observaciones"};

    
    public DATPrestamos(DATConnection datConnection) {
        mConnection = datConnection;
    }
    
    public ResultSet selectPrestamos() throws ClassNotFoundException, SQLException {
        Statement stmt = mConnection.getConnection().createStatement();
        
        String sql = "SELECT * FROM biblioteca.prestamos P, biblioteca.detalle_prestamo D, biblioteca.material M, biblioteca.personas PE "
                + "WHERE P.id_Prestamo = D.ID_Prestamos AND D.id_Material = M.id_Material AND P.id_Personas = PE.id_Personas "
                + "ORDER BY id_Prestamo";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    
    public int insertPrestamo(int persona, String fechaPrestamo, 
            String fechaDevolucion, int estatus, String observaciones) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO prestamos (id_Personas, Fecha_Prestamo, Fecha_Devolucion, Estatus, Observaciones)"
                + "VALUES (?,?,?,?,?)";
        
        PreparedStatement ps = mConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, persona);
        ps.setString(2, fechaPrestamo);
        ps.setString(3, fechaDevolucion);
        ps.setInt(4, estatus);
        ps.setString(5, observaciones);
        
        return ps.executeUpdate();
    }
    
    public void updatePrestamo(int iCol, int idPrestamo, Object value) throws SQLException {
        String sql = "UPDATE prestamos SET " + colNames[iCol] + " = ? WHERE id_Prestamo = ?";
        
        PreparedStatement ps = mConnection.getConnection().prepareStatement(sql);
        ps.setObject(1, value);
        ps.setInt(2, idPrestamo);
        
        ps.executeUpdate();
    }
    
    public ResultSet getIdPrestamo() throws ClassNotFoundException, SQLException {
        String sql = "SELECT max(id_Prestamo) FROM prestamos";
        PreparedStatement ps = mConnection.getConnection().prepareStatement(sql);
        return ps.executeQuery();
    }
    
    public int insertDetallePrestamo(int idPrestamo, int idMaterial) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO detalle_prestamo (id_Prestamos, id_Material)"
                + "VALUES (?,?)";
        
        PreparedStatement ps = mConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, idPrestamo);
        ps.setInt(2, idMaterial);
        return ps.executeUpdate();
    }
}
