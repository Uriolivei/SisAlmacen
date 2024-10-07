package Entidades;

public class Rol {
    private int idrol;
    private String nombre;
    private String descripcion;

    public Rol() {
        
    }

    public Rol(int idrol, String nombre, String descripcion) {
        this.idrol = idrol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
