package modelo;

import java.util.Date;

public class Gasto {
    
    private int id_gasto = 0;
    private int id_usuario;
    private int monto;
    private Date fecha;
    private String nombre_gasto;
    private boolean esUnico = false;

    public Gasto(int id_usuario, int monto, Date fecha, String nombre_gasto, boolean esUnico) {
        this.id_usuario = id_usuario;
        this.monto = monto;
        this.fecha = fecha;
        this.nombre_gasto = nombre_gasto;
        this.esUnico = esUnico;
    }

    public int getId_gasto() {
        return id_gasto;
    }

    public void setId_gasto(int id_gasto) {
        this.id_gasto = id_gasto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre_gasto() {
        return nombre_gasto;
    }

    public void setNombre_gasto(String nombre_gasto) {
        this.nombre_gasto = nombre_gasto;
    }

    public boolean isEsUnico() {
        return esUnico;
    }

    public void setEsUnico(boolean esUnico) {
        this.esUnico = esUnico;
    }

    @Override
    public String toString() {
        return "Gasto{" + "id_gasto=" + id_gasto + ", id_usuario=" + id_usuario + ", monto=" + monto + ", fecha=" + fecha + ", nombre_gasto=" + nombre_gasto + ", esUnico=" + esUnico + '}';
    }
    
    
    
}
