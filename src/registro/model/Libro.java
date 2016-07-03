package registro.model;

public class Libro extends MaterialBibliografico {
    private String mEditorial;
    
    public Libro(String codigo, String autor, String titulo, int anio, boolean estatus,
            String editorial) {
        super(codigo, autor, titulo, anio, estatus);
        mEditorial = editorial;
    }
    
    public Libro(String autor, String titulo, int anio, boolean estatus,
            String editorial) {
        super(autor, titulo, anio, estatus);
        mEditorial = editorial;
    }

    public String getEditorial() {
        return mEditorial;
    }

    
    
    
}
