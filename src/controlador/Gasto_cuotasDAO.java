package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.TarjetaCredito;
import modelo.GastoCuotas;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelo.DetalleCuotas;
import modelo.Usuario;

public class Gasto_cuotasDAO {
    
    public int ingresarGasto(TarjetaCredito tar, GastoCuotas gas)
    {
        int resultado = 0;
        
        int id_tarjeta = tar.getId_tarjeta();
        int num_cuotas = gas.getNum_cuotas();
        int monto_cuotas = gas.getMonto_cuotas();
        Date fecha_gasto = Date.valueOf(gas.getFecha().toLocalDate());
        int monto_total = gas.getMonto_total();
        
        try {
            Connection con = Conexion.getConexion();
            String query = "insert into GASTO_CUOTAS (id_tarjeta,numero_cuotas,monto_cuotas,fecha_gasto,monto_total,nombre_gasto) values(?,?,?,?,?,?)";
            try (PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, id_tarjeta);
                ps.setInt(2, num_cuotas);
                ps.setInt(3, monto_cuotas);
                ps.setDate(4, fecha_gasto);
                ps.setInt(5, monto_total);
                ps.setString(6, gas.getNombreGasto());               
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    resultado = rs.getInt(1);
                }
                
            }
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Gasto_cuotasDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }    
        
        return resultado;       
    }
    
    public GastoCuotas buscarGastoCuotas(Usuario usu, GastoCuotas gas) {
        
        GastoCuotas res = null;
        
        try {
            Connection con = Conexion.getConexion();
            
            String query = "select * from GASTO_CUOTAS where id_gasto_cuota = "+gas.getId_gasto();
            PreparedStatement ps = con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            Date fecha = rs.getDate(5);
            int dia = fecha.getDay();
            int mes = fecha.getMonth();
            int anio = fecha.getYear();
            
            LocalDateTime fechaIngresada = LocalDateTime.of(anio, mes, dia, 12, 00);
            
            while (rs.next()) {
                res = new GastoCuotas(Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)), fechaIngresada, Integer.parseInt(rs.getString(6)), rs.getString(7));
                res.setId_gasto(Integer.parseInt(rs.getString(1)));
                res.setId_tarjeta(Integer.parseInt(rs.getString(2)));
            }
           
          
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TarjetaCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public boolean ingresarDetalle(GastoCuotas gas)
    {
        boolean resultado = false;
        ArrayList<DetalleCuotas> lista = gas.getListaCuotas();
        
        int id_gasto_cuotas = gas.getId_gasto();
        
        try {
            Connection con = Conexion.getConexion();
            String query = "insert into DETALLE_CUOTAS (id_gasto_cuota,num_cuota,fecha_cuota) values(?,?,?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                
                ps.setInt(1, id_gasto_cuotas);
                for (DetalleCuotas det : lista){
                    ps.setInt(2, det.getNum_cuota());
                    Date fecha = Date.valueOf(det.getFecha_cuota().toLocalDate());
                    ps.setDate(3, fecha);
                    resultado = ps.executeUpdate() == 1;
                }            
            }
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Gasto_cuotasDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }    
        
        return resultado;       
    }
    
}
