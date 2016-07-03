package registro.model;

import java.util.Date;
import java.util.List;

public class Prestamo {
    
    private List<MaterialBibliografico> mMateriales;
    private Persona mPersona;
    private String mId;
    private Date mFechaPrestamo;
    private Date mFechaDevolucion;
    private boolean mEstatus;
    private String mObservaciones;

    public Prestamo(List<MaterialBibliografico> mMateriales, Persona mPersona, String mCodigo, Date mFecha,
            Date mFechaDevolucion, boolean estatus, String observaciones) {
        this.mMateriales = mMateriales;
        this.mPersona = mPersona;
        this.mId = mCodigo;
        this.mFechaPrestamo = mFecha;
        this.mFechaDevolucion = mFechaDevolucion;
        this.mEstatus = estatus;
        this.mObservaciones = observaciones;
    }
    
    public Prestamo(List<MaterialBibliografico> mMateriales, Persona mPersona, Date mFecha,
            Date mFechaDevolucion, boolean estatus, String observaciones) {
        this.mMateriales = mMateriales;
        this.mPersona = mPersona;
        this.mFechaPrestamo = mFecha;
        this.mFechaDevolucion = mFechaDevolucion;
        this.mEstatus = estatus;
        this.mObservaciones = observaciones;
    }

    public List<MaterialBibliografico> getMateriales() {
        return mMateriales;
    }

    public Persona getPersona() {
        return mPersona;
    }

    public String getCodigo() {
        return mId;
    }

    public Date getFecha() {
        return mFechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return mFechaDevolucion;
    }

    public boolean getEstatus() {
        return mEstatus;
    }

    public String getObservaciones() {
        return mObservaciones;
    }
    
    
}
