package registro.model;

public class Revista extends MaterialBibliografico {
    private int mNumero;
    
    public Revista(String codigo, String autor, String titulo, int anio, boolean estatus,
            int numero) {
        super(codigo, autor, titulo, anio, estatus);
        mNumero = numero;
    }

    public Revista(String autor, String titulo, int anio, boolean estatus,
            int numero) {
        super(autor, titulo, anio, estatus);
        mNumero = numero;
    }
    
    public int getNumero() {
        return mNumero;
    }
    
    
}
