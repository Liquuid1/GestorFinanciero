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
import modelo.Ingresos;
import modelo.Usuario;

public class IngresosDAO {
    
    public boolean registrarIngreso(Ingresos ing, Usuario usu)
    {
        boolean resultado = false;
        
        try {
            Connection con = Conexion.getConexion();
            String query="insert into INGRESOS (id_usuario,monto,fecha_ingreso,nombre_ingreso,fijo) values(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            
            Date fechaSQL = Date.valueOf(ing.getFecha());
            
            ps.setInt(1, usu.getIdUsuario());
            ps.setInt(2, ing.getMonto());
            ps.setDate(3, fechaSQL);
            ps.setString(4, ing.getNombre_ingreso());
            
            if (ing.isFijo()){
                ps.setInt(5, 1);
            } else {
                ps.setInt(5, 0);
            }

            
            resultado = ps.executeUpdate()==1;
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return resultado;        
    }
    
    public int sumarIngresosResumen(LocalDate previo, LocalDate siguiente, Usuario usu){
        
        int resultado = 0;
        try {
            Connection con = Conexion.getConexion();
            String query="SELECT SUM(monto) FROM ingresos WHERE id_usuario = "+usu.getIdUsuario()+" and (fecha_ingreso between '"+previo.toString()
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
    
    public void actualizarListaIngresos(int mes, int anio, ArrayList<Ingresos> lista, Usuario usu) {
        
        LocalDate previo = LocalDate.of(anio, mes, 01);
        LocalDate siguiente = previo.plusMonths(1).minusDays(1);
        
        String nombre;
        int id;
        int monto;
        Date fecha;
        LocalDate fechaIng;
        boolean fijo;
        
        Ingresos ing;
        
        try {
            Connection con = Conexion.getConexion();
            String query = "SELECT id_ingreso, monto, fecha_ingreso as fecha, nombre_ingreso as nombre, fijo "
                    + "FROM ingresos WHERE (fecha_ingreso between ? and ? or fijo = 1) and id_usuario = ?";
            
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
               
                fechaIng = fecha.toLocalDate();
                
                if (rs.getInt(5) == 1) {
                    fijo = true;
                } else {
                    fijo = false;
                }
                ing = new Ingresos(monto, fechaIng, nombre, fijo);
                ing.setId_ingreso(id);
                ing.setId_usuario(usu.getIdUsuario());
                lista.add(ing);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        
    }
    
    public boolean eliminarIngreso(int id, Usuario usu) {
        
        boolean res = false;
        try {
            Connection con = Conexion.getConexion();
            String query = "DELETE FROM ingresos WHERE id_usuario = ? and id_ingreso = ?";
            
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
    
    public boolean modificarIngreso(int id, String nombre,int monto,String fecha){
        boolean res = false;
        try {
            Connection con = Conexion.getConexion();
            String query = "UPDATE ingresos SET nombre_ingreso = ?, monto = ?, fecha_ingreso = ? WHERE id_ingreso = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, nombre);
            ps.setInt(2, monto);
            ps.setString(3, fecha);
            ps.setInt(4, id);
            
            
            res = ps.executeUpdate() == 1;
            ps.close();
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return res;
        
    }
    
    
}
