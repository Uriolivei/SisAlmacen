package Entidades;

public class Usuario {
    //atributos claswe usuario
    private int idusuario;
    private int idrol;
    private String Rolnombre;
    private String nombre;
    private String tipo_documento;
    private String documento;
    private String direccion;
    private String telefono;
    private String email;
    private String clave;
    private boolean condicion;

    public Usuario() {
        
    }

    public Usuario(int idusuario, int idrol, String Rolnombre, String nombre, String tipo_documento, String documento, String direccion, String telefono, String email, String clave, boolean condicion) {
        this.idusuario = idusuario;
        this.idrol = idrol;
        this.Rolnombre = Rolnombre;
        this.nombre = nombre;
        this.tipo_documento = tipo_documento;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.condicion = condicion;
    }

    public Usuario(int idusuario, int idrol, String Rolnombre, String nombre, String tipo_documento, String documento, String direccion, String telefono, String email, boolean condicion) {
        this.idusuario = idusuario;
        this.idrol = idrol;
        this.Rolnombre = Rolnombre;
        this.nombre = nombre;
        this.tipo_documento = tipo_documento;
        this.documento = documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.condicion = condicion;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getRolnombre() {
        return Rolnombre;
    }

    public void setRolnombre(String Rolnombre) {
        this.Rolnombre = Rolnombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    
}
