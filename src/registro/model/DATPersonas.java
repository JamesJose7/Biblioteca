package registro.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DATPersonas {
    private DATConnection mDATConnection; 
    
    private String[] colNames = {
        "id_Personas", "Nombre", "Correo", "Telefono", "Tipo", "SpecialAttr"};

    
    public DATPersonas(DATConnection datConnection) {
        mDATConnection = datConnection;
    }
    
    public ResultSet selectPersonas() throws ClassNotFoundException, SQLException {
        Statement stmnt = mDATConnection.getConnection().createStatement();
        
        String query = "SELECT * FROM personas";
        ResultSet rs = stmnt.executeQuery(query);
        return rs;
    }
    
    public void insertPersona(Persona persona) throws SQLException {
        Statement stmt = mDATConnection.getConnection().createStatement();
        
        String query = String.format("INSERT INTO personas(Nombre, Correo, Telefono, Tipo, SpecialAttr) "
                        + "VALUES ('%s','%s','%s','%s',",
                        persona.getNombres(),
                        persona.getCorreo(),
                        persona.getTelefono(),
                        formatString(persona.getClass().getSimpleName())
                );

        switch (persona.getClass().getSimpleName().toLowerCase()) {
            case Persona.ALUMNO:
                Alumno alumno = (Alumno) persona;
                
                query += String.format("'%s')",
                        alumno.getIdMatricula()
                );
                
                break;
            case Persona.DOCENTE:
                Docente docente = (Docente) persona;
                
                query += String.format("'%s')",
                        docente.getCodigoDocente()
                );
                break;
            default:
        }
        
        stmt.executeUpdate(query);
    }
    
        public void updatePersona(int iCol, int idPersona, Object value) throws SQLException {
        String sql = "UPDATE personas SET " + colNames[iCol] + " = ? WHERE id_Personas = ?";
        
        PreparedStatement ps = mDATConnection.getConnection().prepareStatement(sql);
        ps.setObject(1, value);
        ps.setInt(2, idPersona);
        
        ps.executeUpdate();
    }
    
    public int deletePersona(int idPersona) throws SQLException {
        String query = "DELETE FROM personas WHERE id_Personas = " + idPersona;
        
        Statement stmt = mDATConnection.getConnection().createStatement();
        
        return stmt.executeUpdate(query);
    }
    
    private String formatString(String s) {
        String firstChar = s.substring(0, 1).toUpperCase();
        String rest = s.substring(1).toLowerCase();
        return firstChar + rest;
    }
}
