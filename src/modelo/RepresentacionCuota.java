package modelo;

import java.util.Date;

public class RepresentacionCuota {
    
    private String nombre;
    private int id_cuota;
    private int monto;
    private Date fecha;
    private int cuotaActual;
    private int cuotaMaxima;

    public RepresentacionCuota(String nombre, int monto, Date fecha, int cuotaActual, int cuotaMaxima) {
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
        this.cuotaActual = cuotaActual;
        this.cuotaMaxima = cuotaMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_cuota() {
        return id_cuota;
    }

    public void setId_cuota(int id_cuota) {
        this.id_cuota = id_cuota;
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

    public int getCuotaActual() {
        return cuotaActual;
    }

    public void setCuotaActual(int cuotaActual) {
        this.cuotaActual = cuotaActual;
    }

    public int getCuotaMaxima() {
        return cuotaMaxima;
    }

    public void setCuotaMaxima(int cuotaMaxima) {
        this.cuotaMaxima = cuotaMaxima;
    }
    
    public String getSalidaCuotas() {
        String res = "("+getCuotaActual()+"/"+getCuotaMaxima()+")";
        return res;
    }

    @Override
    public String toString() {
        return "RepresentacionCuota{" + "nombre=" + nombre + ", id_cuota=" + id_cuota + ", monto=" + monto + ", fecha=" + fecha + ", cuotaActual=" + cuotaActual + ", cuotaMaxima=" + cuotaMaxima + '}';
    }
    
    
    

    
    
    
    
}
