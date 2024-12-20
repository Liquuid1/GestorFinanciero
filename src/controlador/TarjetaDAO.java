package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.RepresentacionCuota;
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
            
            String query = "select * from TARJETA_CREDITO where nombre_tarjeta = '"+tar.getNombre_tarjeta()+"'"
                    + " and id_usuario = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, usu.getIdUsuario());
            
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
                lista.add(agregar);
            }
           
          
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TarjetaCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public int sumarGastosCuotasResumen(LocalDate previo, LocalDate siguiente, Usuario usu){
        
        int resultado = 0;
        try {
            Connection con = Conexion.getConexion();
            String query="SELECT SUM(monto_cuotas) " +
             "FROM gasto_cuotas g " +
             "JOIN detalle_cuotas d ON g.id_gasto_cuota = d.id_gasto_cuota " +
             "JOIN tarjeta_credito t ON g.id_tarjeta = t.id_tarjeta " +
             "WHERE fecha_cuota BETWEEN ? AND ? AND id_usuario = ?";
            
            PreparedStatement ps=con.prepareStatement(query);

            ps.setDate(1, java.sql.Date.valueOf(previo.toString())); // Convertir la fecha a SQL Date
            ps.setDate(2, java.sql.Date.valueOf(siguiente.toString()));
            ps.setInt(3, usu.getIdUsuario()); // Asegurar tipo entero para id_usuario
            
            
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                resultado = rs.getInt(1);
            }
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return resultado; 
        
    }
    
    public void actualizarListaCuotas(int mes, int anio, ArrayList<RepresentacionCuota> lista, Usuario usu) {
        
        LocalDate previo = LocalDate.of(anio, mes, 01);
        LocalDate siguiente = previo.plusMonths(1).minusDays(1);
        int monto;
        java.util.Date fecha;
        String nombre;
        int cuota;
        int maxCuota;
        RepresentacionCuota cuo;
        
        
        try {
            Connection con = Conexion.getConexion();
            String query = "SELECT nombre_gasto as nombre, monto_cuotas as monto, fecha_cuota as fecha, num_cuota as cuota, numero_cuotas as maxCuotas, d.id_gasto_cuota " +
                            "FROM detalle_cuotas d join gasto_cuotas g on d.id_gasto_cuota = g.id_gasto_cuota "
                          + "JOIN tarjeta_credito t on g.id_tarjeta = t.id_tarjeta  " +
                            "WHERE fecha_cuota between ? and ? and id_usuario = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setDate(1, java.sql.Date.valueOf(previo));
            ps.setDate(2, java.sql.Date.valueOf(siguiente));
            ps.setInt(3, usu.getIdUsuario());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                nombre = rs.getString(1);
                monto = rs.getInt(2);
                fecha = rs.getDate(3);
                cuota = rs.getInt(4);
                maxCuota = rs.getInt(5);
                
                cuo = new RepresentacionCuota(nombre, monto, fecha, cuota, maxCuota);
                cuo.setId_cuota(rs.getInt(6));
                lista.add(cuo);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
    }
    
    public boolean eliminarGastoCuotas(int id, int num_cuo){
        
        boolean res = false;
        try {
            Connection con = Conexion.getConexion();
            String query = "DELETE FROM detalle_cuotas WHERE id_gasto_cuota = ? and num_cuota = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, id);
            ps.setInt(2, num_cuo);
            
            
            res = ps.executeUpdate() == 1;
            ps.close();
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return res;
    }
    
}
