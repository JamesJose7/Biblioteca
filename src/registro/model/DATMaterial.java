package registro.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DATMaterial {

    DATConnection mDATConnection;

    public DATMaterial(DATConnection datConnection) {
        mDATConnection = datConnection;
    }

    public ResultSet selectMaterial() throws ClassNotFoundException, SQLException {
        Statement stmt = mDATConnection.getConnection().createStatement();

        String query = "SELECT * FROM material";
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public int insertMaterial(MaterialBibliografico material) throws SQLException {
        Statement stmt = mDATConnection.getConnection().createStatement();
        
        String query = String.format("INSERT INTO material(Autor, Titulo, Anio, Estado, Tipo, SpecialAttr) "
                        + "VALUES ('%s','%s',%d,%d,'%s',",
                        material.getAutor(),
                        material.getTitulo(),
                        material.getAnio(),
                        boolToInt(material.getEstatus()),
                        formatString(material.getClass().getSimpleName())
                );

        switch (material.getClass().getSimpleName().toLowerCase()) {
            case MaterialBibliografico.LIBRO:
                Libro libro = (Libro) material;
                
                query += String.format("'%s')",
                        libro.getEditorial()
                );
                
                break;
            case MaterialBibliografico.REVISTA:
                Revista revista = (Revista) material;
                
                query += String.format("%d)",
                        revista.getNumero()
                );
                break;
            case MaterialBibliografico.TESIS:
                Tesis tesis = (Tesis) material;
                
                query += String.format("%d)",
                        tesis.getCodigoTesis()
                );
                break;
            default:
        }
        
        return stmt.executeUpdate(query);
    }
    
    public int deleteMaterial(int idMaterial) throws SQLException {
        String query = "DELETE FROM material WHERE id_Material = " + idMaterial;
        
        Statement stmt = mDATConnection.getConnection().createStatement();
        
        return stmt.executeUpdate(query);
    }


    private int boolToInt(boolean b) {
        if (b) {
            return 1;
        }
        return 0;
    }

    private String formatString(String s) {
        String firstChar = s.substring(0, 1).toUpperCase();
        String rest = s.substring(1).toLowerCase();
        return firstChar + rest;
    }
}
