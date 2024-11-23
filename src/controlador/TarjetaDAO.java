package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.TarjetaCredito;
import modelo.Usuario;

public class TarjetaDAO {
    
    public boolean ingresarTarjetaCredito(Usuario usu, TarjetaCredito tar)
    {
        boolean resultado = false;
        
        try {
            Connection con = Conexion.getConexion();
            
            String query = "insert into TARJETA_CREDITO (id_usuario,nombre_tarjeta,limite_credito,fecha_facturacion) values(?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, usu.getIdUsuario());
            ps.setString(2, tar.getNombre_tarjeta());
            ps.setInt(3, tar.getCupo());
            ps.setInt(4, tar.getDiaFacturacion());
            
            resultado = ps.executeUpdate() == 1;
            ps.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TarjetaCredito.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return resultado;
    }
    
    public TarjetaCredito buscarTarjetaCredito(Usuario usu, TarjetaCredito tar) {
        
        TarjetaCredito res = null;
        
        try {
            Connection con = Conexion.getConexion();
            
            String query = "select * from TARJETA_CREDITO where nombre_tarjeta = '"+tar.getNombre_tarjeta()+"'";
            PreparedStatement ps = con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                res = new TarjetaCredito(Integer.parseInt(rs.getString(2)), rs.getString(3), Integer.parseInt(rs.getString(4)), 
                        Integer.parseInt(rs.getString(5)));
                res.setId_tarjeta(Integer.parseInt(rs.getString(1)));
            }
           
          
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TarjetaCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
        public void buscarTarjetasCredito(Usuario usu, ArrayList<TarjetaCredito> lista) {
        
        TarjetaCredito agregar = null;
        try {
            Connection con = Conexion.getConexion();
            
            String query = "select * from TARJETA_CREDITO where id_usuario = "+usu.getIdUsuario();
            PreparedStatement ps = con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                agregar = new TarjetaCredito(Integer.parseInt(rs.getString(2)), rs.getString(3), Integer.parseInt(rs.getString(4)), 
                        Integer.parseInt(rs.getString(5)));
                agregar.setId_tarjeta(Integer.parseInt(rs.getString(1)));
                System.out.println(agregar);
                lista.add(agregar);
            }
           
          
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TarjetaCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("HOLAMUNDA");
    }
    
}
