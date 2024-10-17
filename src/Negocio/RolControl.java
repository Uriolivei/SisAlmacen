package Negocio;

import Datos.RolDAO;
import Entidades.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RolControl {
    private final RolDAO DATOS;
    private Rol obj;
    private DefaultTableModel modeloTabla;
    private int registrosMostrados;
    
    public RolControl(){
        this.DATOS=new RolDAO();
        this.obj=new Rol();
        this.registrosMostrados=0;
    }
    
    public DefaultTableModel listar(String texto){
        List<Rol> lista=new ArrayList();
        lista.addAll(DATOS.listar());
        
        String[] titulos={"Id","Nombre","Descripción","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        
        
        String estado;
        String[] registro = new String[4];
        
        this.registrosMostrados=0;
        for (Rol item:lista){
            if(item.getCondicion()){
                estado="Activo";
            }else{
                estado="Inactivo";
            }            
            registro[0]=Integer.toString(item.getIdrol());
            registro[1]=item.getNombre();
            registro[2]=item.getDescripcion();
            registro[3]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public String insertar(String nombre, String descripcion){
        if(DATOS.existe(nombre)){
            return "El nombre del rol  se encuentra en nuestra BD";
        }else{
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            if(DATOS.insertar(obj)){
                return "OK";
            }else{
                return "Error al registar Categoria";
            }
        }
    }
    
    public String actualizar(int id,String nombre,String nombreAt,String descripcion){
        if(nombre.equals(nombreAt)){
            obj.setIdrol(id);
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            if(DATOS.actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualización";
            }
        }else{
            if(DATOS.existe(nombre)){
                return "El rol ya existe";
            }else{
                obj.setIdrol(id);
                obj.setNombre(nombre);
                obj.setDescripcion(descripcion);
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
            return "No se puede desactivar la rol";
        }
    }
    
    public String activar(int id){
        if(DATOS.activar(id)){
            return "OK";
        }else{
            return "No se puede activar la rol";
        }
    }
}
