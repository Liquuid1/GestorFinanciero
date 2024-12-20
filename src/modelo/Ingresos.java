package modelo;

import java.time.LocalDate;

public class Ingresos {
    private int id_ingreso = 0;
    private int id_usuario = 0;
    private int monto;
    private LocalDate fecha;
    private String nombre_ingreso;
    private boolean fijo;

    public Ingresos(int monto, LocalDate fecha, String nombre_ingreso, boolean fijo) {
        this.monto = monto;
        this.fecha = fecha;
        this.nombre_ingreso = nombre_ingreso;
        this.fijo = fijo;
    }

    public int getId_ingreso() {
        return id_ingreso;
    }

    public void setId_ingreso(int id_ingreso) {
        this.id_ingreso = id_ingreso;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre_ingreso() {
        return nombre_ingreso;
    }

    public void setNombre_ingreso(String nombre_ingreso) {
        this.nombre_ingreso = nombre_ingreso;
    }

    public boolean isFijo() {
        return fijo;
    }

    public void setFijo(boolean fijo) {
        this.fijo = fijo;
    }

    @Override
    public String toString() {
        return "Ingresos{" + "id_ingreso=" + id_ingreso + ", id_usuario=" + id_usuario + ", monto=" + monto + ", fecha=" + fecha + ", nombre_ingreso=" + nombre_ingreso + ", fijo=" + fijo + '}';
    }
    
    
    
}
