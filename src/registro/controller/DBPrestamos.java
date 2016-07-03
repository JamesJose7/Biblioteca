package registro.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import registro.model.Alumno;
import registro.model.DATPersonas;
import registro.model.DATPrestamos;
import registro.model.Docente;
import registro.model.Libro;
import registro.model.MaterialBibliografico;
import registro.model.Persona;
import registro.model.Prestamo;
import registro.model.Revista;
import registro.model.Tesis;

/**
 *
 * @author Jose
 */
public class DBPrestamos {

    private DATPrestamos prestamosManager;
    public static DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
    //TODO: detalle prestamo
    private List<Prestamo> mPrestamos;

    public DBPrestamos(DBConnection dbConnection) {
        prestamosManager = new DATPrestamos(dbConnection.getDATConnection());
    }

    public List<Prestamo> consultar() throws ClassNotFoundException, SQLException {
        ResultSet rs = prestamosManager.selectPrestamos();

        mPrestamos = new ArrayList<>();
        int currentID = 0;
        int counter = 0;

        while (rs.next()) {
            List<MaterialBibliografico> materiales = new ArrayList<>();

            if (counter == 0) {
                currentID = rs.getInt("id_Prestamo");
            }
            counter++;

            int id = rs.getInt("id_Prestamo");

            Date fechaPrestamo = rs.getDate("Fecha_Prestamo");
            Date fechaDevolucion = rs.getDate("Fecha_Devolucion");
            boolean estado = getEstadoAsBool(rs.getInt("Estado"));
            String Observaciones = rs.getString("Observaciones");

            //Get persona
            Persona persona = null;
            String idPersona = rs.getInt("id_Personas") + "";
            String nombrePersona = rs.getString("Nombre");
            String correoPersona = rs.getString("Correo");
            String telefonoPersona = rs.getString("Telefono");
            String tipoPersona = rs.getString(20);
            String specialAttrP = rs.getString(21);

            switch (tipoPersona.toLowerCase()) {
                case Persona.ALUMNO:
                    persona = (new Alumno(idPersona, nombrePersona, correoPersona, telefonoPersona, specialAttrP));
                    break;
                case Persona.DOCENTE:
                    persona = (new Docente(idPersona, nombrePersona, correoPersona, telefonoPersona, specialAttrP));
                    break;
                default:
            }

            boolean hasNext = true;
            while (id == currentID && hasNext) {
                currentID = rs.getInt("id_Prestamo");
                if (id == currentID) {

                    String codigo = rs.getInt("id_Material") + "";
                    String autor = rs.getString("Autor");
                    String titulo = rs.getString("Titulo");
                    int anio = rs.getInt("Anio");
                    boolean estadoM = DBManager.getEquivalentBool(rs.getInt("Estado"));
                    String tipo = rs.getString("Tipo");
                    String specialAtrr = rs.getString("SpecialAttr");

                    switch (tipo.toLowerCase()) {
                        case MaterialBibliografico.LIBRO:
                            materiales.add(new Libro(codigo, autor, titulo, anio, estadoM, specialAtrr));
                            break;
                        case MaterialBibliografico.REVISTA:
                            materiales.add(new Revista(codigo, autor, titulo, anio, estadoM, Integer.parseInt(specialAtrr)));
                            break;
                        case MaterialBibliografico.TESIS:
                            materiales.add(new Tesis(codigo, autor, titulo, anio, estadoM, Integer.parseInt(specialAtrr)));
                            break;
                        default:
                    }
                    
                    hasNext = rs.next();
                } else {
                    rs.previous();
                }
            }
            mPrestamos.add(new Prestamo(materiales, persona, id + "", fechaPrestamo, fechaDevolucion, estado, Observaciones));
        }

        return mPrestamos;
    }

    public int insertarPrestamo(Prestamo prestamo) throws ClassNotFoundException, SQLException {
        int isPrestamoInserted = 0;
        int isDetallePrestamoInserted = 0;
        int master = 0;
        ResultSet rs;
        Date currentDate = new Date();

        //Insertar cabecera de prestamo
        isPrestamoInserted = prestamosManager.insertPrestamo(
                Integer.parseInt(prestamo.getPersona().getId()),
                dateFormat.format(currentDate),
                dateFormat.format(prestamo.getFechaDevolucion()),
                getEstadoAsInt(prestamo.getEstatus()),
                prestamo.getObservaciones());

        if (isPrestamoInserted == 1) {
            rs = prestamosManager.getIdPrestamo();
            while (rs.next()) {
                //obtiene el id del registro master 
                master = rs.getInt(1);
            }
        }

        //insert detalle prestamos
        for (MaterialBibliografico material : prestamo.getMateriales()) {
            //id de material
            int idMaterial = Integer.parseInt(material.getCodigo());
            //inserta el detalle del prestamo
            isDetallePrestamoInserted = prestamosManager.insertDetallePrestamo(master, idMaterial);
        }

        return isDetallePrestamoInserted;
    }

    private int getEstadoAsInt(boolean estado) {
        if (estado) {
            return 1;
        }
        return 0;
    }

    private boolean getEstadoAsBool(int estado) {
        if (estado == 1) {
            return true;
        }
        return false;
    }
}
