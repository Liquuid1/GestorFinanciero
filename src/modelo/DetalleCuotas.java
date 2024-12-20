package modelo;

import java.time.LocalDateTime;

public class DetalleCuotas {
    private int id_gasto_cuotas = 0;
    private int num_cuota;
    private LocalDateTime fecha_cuota;

    public DetalleCuotas(int num_cuota, LocalDateTime fecha_cuota) {
        this.num_cuota = num_cuota;
        this.fecha_cuota = fecha_cuota;
    }

    public int getId_gasto_cuotas() {
        return id_gasto_cuotas;
    }

    public void setId_gasto_cuotas(int id_gasto_cuotas) {
        this.id_gasto_cuotas = id_gasto_cuotas;
    }

    public int getNum_cuota() {
        return num_cuota;
    }

    public void setNum_cuota(int num_cuota) {
        this.num_cuota = num_cuota;
    }

    public LocalDateTime getFecha_cuota() {
        return fecha_cuota;
    }

    public void setFecha_cuota(LocalDateTime fecha_cuota) {
        this.fecha_cuota = fecha_cuota;
    }

    @Override
    public String toString() {
        return "DetalleCuotas{" + "id_gasto_cuotas=" + id_gasto_cuotas + ", num_cuota=" + num_cuota + ", fecha_cuota=" + fecha_cuota + "}\n";
    }
    
    
    
}
