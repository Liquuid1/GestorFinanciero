package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GastoCuotas {
    
    private int id_gasto = 0;
    private int id_tarjeta = 0;
    private int num_cuotas;
    private int monto_cuotas;
    private LocalDateTime fecha;
    private int monto_total;
    private String nombreGasto;
    private ArrayList<DetalleCuotas> listaCuotas;

    public GastoCuotas(int num_cuotas, int monto_cuotas, LocalDateTime fecha,int monto_total,  String nombre) {
        this.num_cuotas = num_cuotas;
        this.monto_cuotas = monto_cuotas;
        this.monto_total = monto_total;
        this.fecha = fecha;
        this.listaCuotas = new ArrayList<DetalleCuotas>();
        this.nombreGasto = nombre;
    }
    
    public void llenarDetalle(TarjetaCredito tar){
        
        LocalDateTime fechaCuota = fecha.plusMonths(1);
        
        if (tar.getDiaFacturacion() < fecha.getDayOfMonth()) {
            fechaCuota = fechaCuota.plusMonths(1);
        }
     
        for (int i = 0; i < this.num_cuotas; i++) {
            DetalleCuotas cuota = new DetalleCuotas(i+1, fechaCuota);
            cuota.setId_gasto_cuotas(this.id_gasto);
            fechaCuota = fechaCuota.plusMonths(1);
            
            listaCuotas.add(cuota);
                    
        }
        
        
    }

    public int getId_gasto() {
        return id_gasto;
    }

    public void setId_gasto(int id_gasto) {
        this.id_gasto = id_gasto;
    }

    
    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public int getNum_cuotas() {
        return num_cuotas;
    }

    public void setNum_cuotas(int num_cuotas) {
        this.num_cuotas = num_cuotas;
    }

    public int getMonto_cuotas() {
        return monto_cuotas;
    }

    public void setMonto_cuotas(int monto_cuotas) {
        this.monto_cuotas = monto_cuotas;
    }

    public int getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public ArrayList<DetalleCuotas> getListaCuotas() {
        return listaCuotas;
    }

    public void setListaCuotas(ArrayList<DetalleCuotas> listaCuotas) {
        this.listaCuotas = listaCuotas;
    }

    public String getNombreGasto() {
        return nombreGasto;
    }

    public void setNombreGasto(String nombreGasto) {
        this.nombreGasto = nombreGasto;
    }

    @Override
    public String toString() {
        return "GastoCuotas{" + "id_gasto=" + id_gasto + ", id_tarjeta=" + id_tarjeta + ", num_cuotas=" + num_cuotas + ", monto_cuotas=" + monto_cuotas + ", monto_total=" + monto_total + ", nombreGasto=" + nombreGasto + ", fecha=" + fecha + ", listaCuotas=" + listaCuotas + '}';
    }
    
    
    
    


    
    
    
    
    
    
    
}
