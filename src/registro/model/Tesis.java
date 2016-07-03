package registro.model;

public class Tesis extends MaterialBibliografico {
    private int mCodigoTesis;
    
    public Tesis(String codigo, String autor, String titulo, int anio, boolean estatus,
        int codigoT) {
        super(codigo, autor, titulo, anio, estatus);
        mCodigoTesis = codigoT;
    }
    
    public Tesis(String autor, String titulo, int anio, boolean estatus,
        int codigoT) {
        super(autor, titulo, anio, estatus);
        mCodigoTesis = codigoT;
    }

    public int getCodigoTesis() {
        return mCodigoTesis;
    }
    
    
}
