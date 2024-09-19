package Negocio;

import Datos.ProductoDAO;
import Entidades.Categoria;
import Entidades.Productos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ProductoControl {
    private final ProductoDAO DATOSCAT;
    private Productos obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrar=0;
    
    public ProductoControl(){
        this.DATOSCAT = new ProductoDAO();
        this.obj = new Productos();
        this.registrosMostrar=0;
    }
    
    //
        public DefaultComboBoxModel seleccionar(){
            DefaultComboBoxModel items = new DefaultComboBoxModel();
            List<Categoria> lista = new ArrayList();
            lista = DATOSCAT.seleccionar();
            for(Categoria item:lista){
                items.addElement(new Categoria(item.getIdcategoria(),item.getNombre()));
            }
            return items;
        }
        
    //métodos para el giro de negocio
    public DefaultTableModel listar(String texto){
        List<Productos> lista = new ArrayList();
        lista.addAll(DATOSCAT.listar(texto));
        
        String[] titulo={"Idproducto","Categoria","Nombre","Descripción","Imagen","Código","Marca","Cantidad","Fecha de vencimiento",
            "Precio","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulo);
        
        String condicion;
        String[] registro = new String[11];
        
        this.registrosMostrar=0;
        
        for(Productos item:lista){
            if(item.isCondicion()){
                condicion="Activo";
            }else{
                condicion="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdproducto());
            registro[1]=item.getCategoria_id();
            registro[2]=item.getNombre_producto();
            registro[3]=item.getDescripcion_producto();
            registro[4]=item.getImagen_producto();
            registro[5]=item.getCodigo_producto();
            registro[6]=item.getMarca_producto();
            registro[7]=Integer.toString(item.getCantidad_producto());
            registro[8]=item.getFecha_vencimiento();
            registro[9]=Double.toString(item.getPrecio_compra());
            registro[10]=condicion;
            this.modeloTabla.addRow(registro);
            this.registrosMostrar++;
        }
        return this.modeloTabla;
    }
    
    public int total(){
        return DATOSCAT.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrar;
    }
    
    //metodo para activar
    public String activar(int id){
        if(DATOSCAT.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el producto";
        }
    }
    
    //metodo para desactivar
    public String desactivar(int id){
        if(DATOSCAT.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar el producto";
        }
    }
}
