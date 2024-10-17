package Negocio;

import Datos.ClientesDAO;
import Entidades.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteControl {
    private final ClientesDAO DATOS;
    private Clientes obj;
    private DefaultTableModel modeloTabla;
    private int registrosMostrados;
    
    public ClienteControl(){
        this.DATOS=new ClientesDAO();
        this.obj=new Clientes();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(String texto){
        List<Clientes> lista=new ArrayList();
        lista.addAll(DATOS.listar());
        
        String[] titulos={"Id","Nombre Cliente","Tipo Documento","Documento","Tipo Cliente","Teléfono","Dirección","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        
        String estado;
        String[] registro = new String[8];
        
        this.registrosMostrados=0;
        for (Clientes item:lista){
            if(item.isCondicion()){
                estado="Activo";
            }else{
                estado="Inactivo";
            }            
            registro[0]=Integer.toString(item.getIdcliente());
            registro[1]=item.getNombre_cliente();
            registro[2]=item.getTipo_documento();
            registro[3]=item.getDocumento();
            registro[4]=item.getTipo_cliente();
            registro[5]=item.getTelefono();
            registro[6]=item.getDireccion();
            registro[7]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String nombre_cliente, String tipo_documento, String documento, String tipo_cliente, String telefono, 
            String direccion){
        if(DATOS.existe(documento)){
            return "El nombre del rol  se encuentra en nuestra BD";
        }else{
            obj.setNombre_cliente(nombre_cliente);
            obj.setTipo_documento(tipo_documento);
            obj.setDocumento(documento);
            obj.setTipo_cliente(tipo_cliente);
            obj.setTelefono(telefono);
            obj.setDireccion(direccion);
            if(DATOS.insertar(obj)){
                return "OK";
            }else{
                return "Error al registar Cliente";
            }
        }
    }
    
    public String actualizar(int id,String nombre, String nombreAnt, String tipo_documento, String documento, String tipo_cliente, String telefono, 
            String direccion){
        if(nombre.equals(nombreAnt)){
            obj.setIdcliente(id);
            obj.setNombre_cliente(nombre);
            obj.setTipo_documento(tipo_documento);
            obj.setDocumento(documento);
            obj.setTipo_cliente(tipo_cliente);
            obj.setTelefono(telefono);
            obj.setDireccion(direccion);
            if(DATOS.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización";
            }
        }else{
            if(DATOS.existe(nombre)){
                return "El rol ya existe";
            }else{
                obj.setIdcliente(id);
                obj.setNombre_cliente(nombre);
                obj.setTipo_documento(tipo_documento);
                obj.setDocumento(documento);
                obj.setTipo_cliente(tipo_cliente);
                obj.setTelefono(telefono);
                obj.setDireccion(direccion);
                if(DATOS.actualizar(obj)){
                    return "OK";
                }else{
                    return "ERROR en la actualización";
                }
            }
        }
        
    }
    
    public int total(){
        return DATOS.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }
    
    public String desactivar(int id){
        if(DATOS.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar la cliente";
        }
    }
    
    public String activar(int id){
        if(DATOS.activar(id)){
            return "OK";
        }else{
            return "No se puede activar la cliente";
        }
    }
}
