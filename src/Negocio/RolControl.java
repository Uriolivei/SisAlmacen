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
    
    public DefaultTableModel listar(){
        List<Rol> lista = new ArrayList();
        lista.addAll(DATOS.listar());
        
        String[] titulos = {"ID","Nombre","Descripción"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registros = new String[3];
        
        this.registrosMostrados=0;
        for(Rol item : lista){
            registros[0] = Integer.toString(item.getIdrol());
            registros[1] = item.getNombre();
            registros[2] = item.getDescripcion();
            this.modeloTabla.addRow(registros);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public int total(){
        return DATOS.total();
    }
    
    public int totalMostrados(){
        return this.registrosMostrados;
    }
}
