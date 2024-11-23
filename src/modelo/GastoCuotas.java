/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author liquu
 */
public class GastoCuotas {
    
    private int id_tarjeta;
    private int num_cuotas;
    private int monto_cuotas;
    private int monto_total;
    private Date fecha;

    public GastoCuotas(int id_tarjeta, int num_cuotas, int monto_cuotas, int monto_total, Date fecha) {
        this.id_tarjeta = id_tarjeta;
        this.num_cuotas = num_cuotas;
        this.monto_cuotas = monto_cuotas;
        this.monto_total = monto_total;
        this.fecha = fecha;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "GastoCuotas{" + "id_tarjeta=" + id_tarjeta + ", num_cuotas=" + num_cuotas + ", monto_cuotas=" + monto_cuotas + ", monto_total=" + monto_total + ", fecha=" + fecha + '}';
    }
    
    
    
    
    
    
    
}
