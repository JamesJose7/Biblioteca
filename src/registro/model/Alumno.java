package registro.model;

public class Alumno extends Persona {
    private String mIdMatricula;
    
    public Alumno(String id, String nombres, String correo, String telefono,
            String idMatricula) {
        super(id, nombres, correo, telefono);
        mIdMatricula = idMatricula;
    }
    
    public Alumno(String nombres, String correo, String telefono,
            String idMatricula) {
        super(nombres, correo, telefono);
        mIdMatricula = idMatricula;
    }
    
    public String getIdMatricula() {
        return mIdMatricula;
    }
}
