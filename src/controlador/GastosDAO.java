package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    
}
