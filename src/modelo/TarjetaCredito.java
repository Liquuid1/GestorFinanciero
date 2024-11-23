package modelo;

public class TarjetaCredito {
    
    private int id_tarjeta = 0, id_usuario, cupo, diaFacturacion;
    private String nombre_tarjeta;

    public TarjetaCredito(int id_usuario, String nombre_tarjeta, int diaFacturacion, int cupo) {
        this.id_usuario = id_usuario;
        this.nombre_tarjeta = nombre_tarjeta;
        this.diaFacturacion = diaFacturacion;
        this.cupo = cupo;
    }

    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getDiaFacturacion() {
        return diaFacturacion;
    }

    public void setDiaFacturacion(int diaFacturacion) {
        this.diaFacturacion = diaFacturacion;
    }

    public String getNombre_tarjeta() {
        return nombre_tarjeta;
    }

    public void setNombre_tarjeta(String nombre_tarjeta) {
        this.nombre_tarjeta = nombre_tarjeta;
    }

    @Override
    public String toString() {
        return "TarjetaCredito{" + "id_tarjeta=" + id_tarjeta + ", id_usuario=" + id_usuario + ", cupo=" + cupo + ", diaFacturacion=" + diaFacturacion + ", nombre_tarjeta=" + nombre_tarjeta + '}';
    }
    
    
    
}
