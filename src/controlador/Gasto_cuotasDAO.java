package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.TarjetaCredito;
import modelo.GastoCuotas;
import java.sql.Date;

public class Gasto_cuotasDAO {
    
    public boolean ingresarGasto(TarjetaCredito tar, GastoCuotas gas)
    {
        boolean resultado = false;
        
        int id_tarjeta = tar.getId_tarjeta();
        int num_cuotas = gas.getNum_cuotas();
        int monto_cuotas = gas.getMonto_cuotas();
        Date fecha_gasto = new Date(gas.getFecha().getTime());
        int monto_total = gas.getMonto_total();
        
        try {
            Connection con = Conexion.getConexion();
            String query = "insert into GASTO_CUOTAS (id_tarjeta,numero_cuotas,monto_cuotas,fecha_gasto,monto_total) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, id_tarjeta);
            ps.setInt(2, num_cuotas);
            ps.setInt(3, monto_cuotas);
            ps.setDate(4, fecha_gasto);
            ps.setInt(5, monto_total);
            
            resultado = ps.executeUpdate() == 1;
            ps.close();
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Gasto_cuotasDAO.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
        
        
        
        
        return resultado;       
    }
    
}
