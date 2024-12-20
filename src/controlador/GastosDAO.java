package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Gasto;
import modelo.Usuario;

public class GastosDAO {
    
    public boolean ingresarGasto(Gasto gas, Usuario usu)
    {
        boolean resultado = false;
        
        try {
            Connection con = Conexion.getConexion();
            String query="insert into GASTO (id_usuario,monto,fecha_gasto,nombre_gasto,fijo) values(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            
            Date fechaSQL = new Date(gas.getFecha().getTime());
            
            ps.setInt(1, usu.getIdUsuario());
            ps.setInt(2, gas.getMonto());
            ps.setDate(3, fechaSQL);
            ps.setString(4, gas.getNombre_gasto());
            
            if (gas.isEsUnico()){
                ps.setInt(5, 0);
            } else {
                ps.setInt(5, 1);
            }

            
            resultado = ps.executeUpdate()==1;
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return resultado;        
    }
    
    public int sumarGastosResumen(LocalDate previo, LocalDate siguiente, Usuario usu){
        
        int resultado = 0;
        try {
            Connection con = Conexion.getConexion();
            String query="SELECT SUM(monto) FROM gasto WHERE id_usuario = "+usu.getIdUsuario()+" and (fecha_gasto between '"+previo.toString()
                    +"' and '"+siguiente.toString()+"' or fijo = 1)";
            PreparedStatement ps=con.prepareStatement(query);
            
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
    
    public void actualizarListaGastos(int mes, int anio, ArrayList<Gasto> lista, Usuario usu) {
        
        LocalDate previo = LocalDate.of(anio, mes, 01);
        LocalDate siguiente = previo.plusMonths(1).minusDays(1);
        int id;
        int monto;
        java.util.Date fecha;
        String nombre;
        boolean fijo;
        Gasto gas;
        
        
        try {
            Connection con = Conexion.getConexion();
            String query = "SELECT id_gasto, monto, fecha_gasto as fecha, nombre_gasto, fijo as nombre "
                    + "FROM gasto WHERE (fecha_gasto between ? and ? or fijo = 1) and id_usuario = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setDate(1, java.sql.Date.valueOf(previo));
            ps.setDate(2, java.sql.Date.valueOf(siguiente));
            ps.setInt(3, usu.getIdUsuario());
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt(1);
                monto = rs.getInt(2);
                fecha = rs.getDate(3);
                nombre = rs.getString(4);
                
                if (rs.getInt(5) == 1) {
                    fijo = false;
                } else {
                    fijo = true;
                }
                gas = new Gasto(usu.getIdUsuario(), monto, fecha, nombre, fijo);
                gas.setId_gasto(id);
                lista.add(gas);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
    }
    
    public boolean eliminarGasto(int id, Usuario usu){
        
        boolean res = false;
        try {
            Connection con = Conexion.getConexion();
            String query = "DELETE FROM gasto WHERE id_usuario = ? and id_gasto = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, usu.getIdUsuario());
            ps.setInt(2, id);
            
            
            res = ps.executeUpdate() == 1;
            ps.close();
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return res;
    }
}
