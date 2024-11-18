package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

public class UsuarioDao {
    
    public boolean registrarUsuario(Usuario usu)
    {
        boolean resultado = false;
        
        try {
            Connection con = Conexion.getConexion();
            String query="insert into USUARIO (nombre_usuario,contrasenia) values(?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            
            ps.setString(1, usu.getNombreUsuario());
            ps.setString(2, usu.getContrasenia());
            
            resultado = ps.executeUpdate()==1;
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);           
        }
        return resultado;        
    }
    
    public Usuario buscarUsuario(String nom)
    {
        Usuario col = null;
        try {
            Connection con = Conexion.getConexion();
            String query = "select * from USUARIO where nombre_usuario ='"+nom+"'";
            PreparedStatement ps = con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            
            while (rs.next()) {
                col = new Usuario(rs.getString(2), rs.getString(3));
                col.setIdUsuario(Integer.parseInt(rs.getString(1)));
            }  
            ps.close();            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return col;           
    }
        
}
    

