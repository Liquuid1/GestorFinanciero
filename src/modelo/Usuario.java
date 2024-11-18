package modelo;

public class Usuario {
    
    private int id_usuario = 0;
    private String nombreUsuario;
    private String contrasenia;

    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }
    
    public int getIdUsuario(){
        return id_usuario;
    }
    
    public void setIdUsuario(int id){
        this.id_usuario = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia + '}';
    }


    
    
    
}
