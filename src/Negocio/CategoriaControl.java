package Negocio;

import Datos.CategoriaDAO;
import Entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CategoriaControl {
    //variables
    private final CategoriaDAO DATOS;
    private Categoria obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados=0;
    
    //constructor
    public CategoriaControl(){
        this.DATOS=new CategoriaDAO();
        this.obj=new Categoria();
        this.registrosMostrados=0;
    }
    
    //métodos para el giro de negocio
    public DefaultTableModel listar(String texto){
        List<Categoria> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));
        
        String[] titulos={"IdCategoria","Nombre Categoria","Descripción Categoria","Condición"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[4];
        
        this.registrosMostrados=0;
        
        for(Categoria item:lista){
            if(item.isCondicion()){
                estado="Activo";
            }else{
                estado="Inactivo";
            }
            registro[0]=Integer.toString(item.getIdcategoria());
            registro[1]=item.getNombre();
            registro[2]=item.getDescripcion();
            registro[3]=estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    
     //metodo registrar categoria
    public String insertar(String nombre, String descripcion){
        if(DATOS.existe(nombre)){
            return "El nombre de la categoria se encuentra en nuestra BD";
        }else{
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            if(DATOS.insertar(obj)){
                return "Oki doki domi doki";
            }else{
                return "Error al registar Categoria";
            }
        }
    }
    
    //metodo para actualizar dtos de la categoria
    public String actualizar(int id,String nombre,String nombreAt,String descripcion){
        if(nombre.equals(nombreAt)){
            obj.setIdcategoria(id);
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            if(DATOS.actualizar(obj)){
                return "Oki doki domi doki";
            }else{
                return "Error en la actualización";
            }
        }else{
            if(DATOS.existe(nombre)){
                return "La categoría ya existe";
            }else{
                obj.setIdcategoria(id);
                obj.setNombre(nombre);
                obj.setDescripcion(descripcion);
                if(DATOS.actualizar(obj)){
                    return "Oki doki domi doki";
                }else{
                    return "ERROR en la actualización";
                }
            }
        }
        
    }
    
    //metodo para retornar el total de registros
    public int total(){
        return DATOS.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }
    
    //metodo para desactivar
    public String desactivar(int id){
        if(DATOS.desactivar(id)){
            return "Oki doki domi doki";
        }else{
            return "No se puede desactivar la categoría";
        }
    }
    
    //metodo para activar
    public String activar(int id){
        if(DATOS.activar(id)){
            return "Oki doki domi doki";
        }else{
            return "No se puede activar la categoría";
        }
    }
}
