package registro.model;

public class Docente extends Persona {
    private String mCodigoDocente;

    public Docente(String id, String nombres, String correo, String telefono,
            String codigoDocente) {
        super(id, nombres, correo, telefono);
        mCodigoDocente = codigoDocente;   
    }
    
    public Docente(String nombres, String correo, String telefono,
            String codigoDocente) {
        super(nombres, correo, telefono);
        mCodigoDocente = codigoDocente;   
    }
    
    public String getCodigoDocente() {
        return mCodigoDocente;
    }
}
