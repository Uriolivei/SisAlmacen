package Entidades;

public class Clientes {
    private int idcliente;
    private String nombre_cliente;
    private String tipo_documento;
    private String documento;
    private String tipo_cliente;
    private String telefono;
    private String direccion;
    private boolean condicion;

    public Clientes() {
        
    }

    public Clientes(int idcliente, String nombre_cliente, String tipo_documento, String documento, String tipo_cliente, String telefono, 
            String direccion, boolean condicion) {
        this.idcliente = idcliente;
        this.nombre_cliente = nombre_cliente;
        this.tipo_documento = tipo_documento;
        this.documento = documento;
        this.tipo_cliente = tipo_cliente;
        this.telefono = telefono;
        this.direccion = direccion;
        this.condicion = condicion;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
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

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "Clientes{" + "idcliente=" + idcliente + ", nombre_cliente=" + nombre_cliente + ", tipo_documento=" + tipo_documento + ", documento=" + documento + ", tipo_cliente=" + tipo_cliente + ", telefono=" + telefono + ", direccion=" + direccion + ", condicion=" + condicion + '}';
    }

}
