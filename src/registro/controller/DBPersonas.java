package registro.controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import registro.model.Alumno;
import registro.model.DATPersonas;
import registro.model.Docente;
import registro.model.Persona;

public class DBPersonas {
    
    DATPersonas personasManager;
    private List<Persona> mPersonas;
    
    public DBPersonas(DBConnection dbConnection) {
        personasManager = new DATPersonas(dbConnection.getDATConnection());
    }
    
    public List<Persona> consultar() throws ClassNotFoundException, SQLException {
        ResultSet rs = personasManager.selectPersonas();
        ResultSetMetaData rm = rs.getMetaData();
        
        int colCount = rm.getColumnCount();
        List<String> columns = new ArrayList<>();
        
        for (int i = 1; i < colCount; i++) {
            String colName = rm.getColumnName(i);
            columns.add(colName);
        }
        
        mPersonas = new ArrayList<>();
        
        while(rs.next()) {
            
            String id = rs.getString("id_Personas");
            String nombre = rs.getString("Nombre");
            String correo = rs.getString("Correo");
            String telefono = rs.getString("Telefono");
            String tipo = rs.getString("Tipo");
            String specialAttr = rs.getString("SpecialAttr");
            
            switch(tipo.toLowerCase()) {
                case Persona.ALUMNO:
                    mPersonas.add(new Alumno(id, nombre, correo, telefono, specialAttr));
                    break;
                case Persona.DOCENTE:
                    mPersonas.add(new Docente(id, nombre, correo, telefono, specialAttr));
                    break;
                default:
            }
        }
        
        return mPersonas;
    }
    
    public void update(int idCol, int idPersona, Object value) throws SQLException {
        personasManager.updatePersona(idCol, idPersona, value);
    }
    
    public void insert(Persona persona) throws SQLException {
        personasManager.insertPersona(persona);
    }
    
    public int delete(int idPersona) throws SQLException {
        return personasManager.deletePersona(idPersona);
    }
}
