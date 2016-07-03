package registro.model;

public class Persona {
    public static final String ALUMNO = "alumno";
    public static final String DOCENTE = "docente";
    
    private String mId;
    private String mNombres;
    private String mCorreo;
    private String mTelefono;

    public Persona(String id, String nombres, String correo, String telefono) {
        mId = id;
        mNombres = nombres;
        mCorreo = correo;
        mTelefono = telefono;
    }

     public Persona(String nombres, String correo, String telefono) {
        mNombres = nombres;
        mCorreo = correo;
        mTelefono = telefono;
    }
    
    public String getId() {
        return mId;
    }

    public String getNombres() {
        return mNombres;
    }

    public String getCorreo() {
        return mCorreo;
    }

    public String getTelefono() {
        return mTelefono;
    }
    
    
}
