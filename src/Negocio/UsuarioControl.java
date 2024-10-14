package Negocio;

import Datos.RolDAO;
import Datos.UsuarioDAO;
import Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioControl {
    private final UsuarioDAO DATOS;
    private final RolDAO DATOSROL;
    private Usuario obj;
    private DefaultTableModel modeloTabla;
    public int registroMostrados;
    
    public UsuarioControl(){
        this.DATOS = new UsuarioDAO();
        this.DATOSROL = new RolDAO();
        this.obj = new Usuario();
        this.registroMostrados=0;
    }
    
    //metodo paara listar usuarios
    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina){
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));
        
        String[] titulos = {"Id","Rol","ID","Rol","Usuario","Documento","Dirección","Teléfono","Email","Clave","Estado"};
        
        String estado;
        String[] registro = new String[11];
        this.registroMostrados=0;
        
        for(Usuario item : lista){
            if(item.isCondicion()){
                estado = "Activo";
            }else{
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getIdusuario());
            registro[1] = Integer.toString(item.getIdrol());
            registro[2] = item.getRolnombre();
            registro[3] = item.getNombre();
            registro[4] = item.getTipo_documento();
            registro[5] = item.getDocumento();
            registro[6] = item.getDireccion();
            registro[7] = item.getTelefono();
            registro[8] = item.getEmail();
            registro[9] = item.getClave();
            registro[10] = estado;
            this.modeloTabla.addRow(registro);
            this.registroMostrados = this.registroMostrados+1;
        }
        return this.modeloTabla;
    }
}
