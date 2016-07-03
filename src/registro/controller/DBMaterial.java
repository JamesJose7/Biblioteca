package registro.controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import registro.model.DATConnection;
import registro.model.DATMaterial;
import registro.model.Libro;
import registro.model.MaterialBibliografico;
import registro.model.Revista;
import registro.model.Tesis;

public class DBMaterial {
    
    DATMaterial materialManager;
    private List<MaterialBibliografico> mMateriales;
    
    public DBMaterial(DBConnection dbConnection) {
        materialManager = new DATMaterial(dbConnection.getDATConnection());
    }
    
    public List<MaterialBibliografico> consultar() throws ClassNotFoundException, SQLException {
        ResultSet rs = materialManager.selectMaterial();
        ResultSetMetaData rm = rs.getMetaData();
        
        int colCount = rm.getColumnCount();
        List<String> columns = new ArrayList<>();
        
        for (int i = 1; i < colCount; i++) {
            String colName = rm.getColumnName(i);
            columns.add(colName);
        }
        
        mMateriales = new ArrayList<>();
        
        while(rs.next()) {
            
            String codigo = rs.getInt("id_Material") + "";
            String autor = rs.getString("Autor");
            String titulo = rs.getString("Titulo");
            int anio = rs.getInt("Anio");
            boolean estado = DBManager.getEquivalentBool(rs.getInt("Estado"));
            String tipo = rs.getString("Tipo");
            String specialAtrr = rs.getString("SpecialAttr");
            
            switch(tipo.toLowerCase()) {
                case MaterialBibliografico.LIBRO:
                    mMateriales.add(new Libro(codigo, autor, titulo, anio, estado, specialAtrr));
                    break;
                case MaterialBibliografico.REVISTA:
                    mMateriales.add(new Revista(codigo, autor, titulo, anio, estado, Integer.parseInt(specialAtrr)));
                    break;
                case MaterialBibliografico.TESIS:
                    mMateriales.add(new Tesis(codigo, autor, titulo, anio, estado, Integer.parseInt(specialAtrr)));
                    break;
                default:
            } 
            
        }
        
        return mMateriales;
    }
    
    public int insert(MaterialBibliografico material) throws SQLException {
        return materialManager.insertMaterial(material);
    }
    
    public int delete(int idMaterial) throws SQLException {
        return materialManager.deleteMaterial(idMaterial);
    }
}
