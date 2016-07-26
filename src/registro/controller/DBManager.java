package registro.controller;

import registro.model.Tesis;
import registro.model.Libro;
import registro.model.MaterialBibliografico;
import registro.model.Prestamo;
import registro.model.Revista;
import registro.model.Persona;
import registro.model.Docente;
import registro.model.Alumno;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    
    private DBConnection mDBConnection;
    private DBMaterial mDBMaterial;
    private DBPersonas mDBPersonas;
    private DBPrestamos mDBPrestamos;

    private List<Prestamo> mPrestamos;
    private List<MaterialBibliografico> mMaterialBibliografico;
    private List<Persona> mPersonas;

    public DBManager() {
        mDBConnection = new DBConnection();
        mDBMaterial = new DBMaterial(mDBConnection);
        mDBPersonas = new DBPersonas(mDBConnection);
        mDBPrestamos = new DBPrestamos(mDBConnection);
    }
    
    public void openConnection() throws ClassNotFoundException, SQLException {
        mDBConnection.openConection();
    }
    
    public void closeConnection() throws SQLException {
        mDBConnection.closeConection();
    }

    public void importMaterial() throws ClassNotFoundException, SQLException {
        mMaterialBibliografico = mDBMaterial.consultar();
    }
    
    public int insertMaterial(MaterialBibliografico material) throws SQLException {
        return mDBMaterial.insert(material);
    }
    
    public void updateMaterial(int idCol, int idMaterial, Object value) throws SQLException {
        mDBMaterial.update(idCol, idMaterial, value);
    }
    
    public int deleteMaterial(int idMaterial) throws SQLException {
        return mDBMaterial.delete(idMaterial);
    }

    public void importPersonas() throws ClassNotFoundException, SQLException {
        mPersonas = mDBPersonas.consultar();
    }
    
    public void insertPersona(Persona persona) throws SQLException {
        mDBPersonas.insert(persona);
    }
    
    public void updatePersona(int idCol, int idPersona, Object value) throws SQLException {
        mDBPersonas.update(idCol, idPersona, value);
    }
    
    public int deletePersona(int idPersona) throws SQLException {
        return mDBPersonas.delete(idPersona);
    }

    public void importPrestamos() throws ClassNotFoundException, SQLException {
        mPrestamos = mDBPrestamos.consultar();
    }
    
    public void insertPrestamo(Prestamo prestamo) throws ClassNotFoundException, SQLException {
        mDBPrestamos.insertarPrestamo(prestamo);
    }
    
    public void updatePrestamo(int idCol, int idCatalogo, Object value) throws SQLException {
        mDBPrestamos.update(idCol, idCatalogo, value);
    }
    
    public List<MaterialBibliografico> getMaterial() {
        return mMaterialBibliografico;
    }
    
    public List<Persona> getPersonas() {
        return mPersonas;
    }

    public List<Prestamo> getPrestamos() {
        return mPrestamos;
    }

    public static boolean getEquivalentBool(int i) {
        if (i == 1) {
            return true;
        }
        return false;
    }
    
    public MaterialBibliografico getMaterialById(int id) {
        for (MaterialBibliografico material : mMaterialBibliografico) {
            if (Integer.parseInt(material.getCodigo()) == id)
                return material;
        }
        return null;
    }
    
    public Persona getPersonaById(int id) {
        for (Persona persona : mPersonas) {
            if (Integer.parseInt(persona.getId()) == id)
                return persona;
        }
        return null;
    }
    
    public Prestamo getPrestamoById(int id) {
        for (Prestamo prestamo : mPrestamos) {
            if (Integer.parseInt(prestamo.getCodigo()) == id)
                return prestamo;
        }
        return null;
    }
}
