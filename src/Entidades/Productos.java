package Entidades;

public class Productos {
    //variables
    private int idproducto;
    private String categoria_id;
    private String nombre_producto;
    private String descripcion_producto;
    private String imagen_producto;
    private String codigo_producto;
    private String marca_producto;
    private int cantidad_producto;
    private String fecha_vencimiento;
    private double precio_compra;
    private boolean condicion;
    
    //constructor vacio

    public Productos() {
    }
    
    //constructor con parametros

    public Productos(int idproducto, String categoria_id, String nombre_producto, String descripcion_producto, String imagen_producto, String codigo_producto, String marca_producto, 
            int cantidad_producto, String fecha_vencimiento, double precio_compra, boolean condicion) {
        this.idproducto = idproducto;
        this.categoria_id = categoria_id;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.imagen_producto = imagen_producto;
        this.codigo_producto = codigo_producto;
        this.marca_producto = marca_producto;
        this.cantidad_producto = cantidad_producto;
        this.fecha_vencimiento = fecha_vencimiento;
        this.precio_compra = precio_compra;
        this.condicion = condicion;
    }
    
    //getter and setter

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(String categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public String getImagen_producto() {
        return imagen_producto;
    }

    public void setImagen_producto(String imagen_producto) {
        this.imagen_producto = imagen_producto;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getMarca_producto() {
        return marca_producto;
    }

    public void setMarca_producto(String marca_producto) {
        this.marca_producto = marca_producto;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
    }
    
    
    //toString

    @Override
    public String toString() {
        return nombre_producto;
    }
    
    
    
}
