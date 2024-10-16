package Entidades;

public class Categoria {
    //atributos
    private int idcategoria;
    private String nombre;
    private String descripcion;
    private boolean condicion;
    
    //constructor sin parametros
    public Categoria() {
    }
    
    //constructor con argumentos y parametros
    public Categoria(int idcategoria, String nombre, String descripcion, boolean condicion) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.condicion = condicion;
    }
    
    //constructor para el id y elnombre para el combobox
    public Categoria(int idcategoria, String nombre){
        this.idcategoria = idcategoria;
        this.nombre = nombre;
    }
    
    //get and setter
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
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

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }
    
    //ToString

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
