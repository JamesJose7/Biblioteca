package registro.model;

public class MaterialBibliografico {
    public static final String LIBRO = "libro";
    public static final String REVISTA = "revista";
    public static final String TESIS = "tesis";
    
    
    private String mID;
    private String mAutor;
    private String mTitulo;
    private int mAnio;
    private boolean mEstatus;

    public MaterialBibliografico(String codigo, String autor, String titulo,
            int anio, boolean estatus) {
        mID = codigo;
        mAutor = autor;
        mTitulo = titulo;
        mAnio = anio;
        mEstatus = estatus;
    }
    
    public MaterialBibliografico(String autor, String titulo,
            int anio, boolean estatus) {
        mAutor = autor;
        mTitulo = titulo;
        mAnio = anio;
        mEstatus = estatus;
    }

    public String getCodigo() {
        return mID;
    }

    public String getAutor() {
        return mAutor;
    }

    public String getTitulo() {
        return mTitulo;
    }

    public int getAnio() {
        return mAnio;
    }

    public boolean getEstatus() {
        return mEstatus;
    }
    
    
}
