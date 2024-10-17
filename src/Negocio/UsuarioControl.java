package Negocio;

import Datos.RolDAO;
import Datos.UsuarioDAO;
import Entidades.Rol;
import Entidades.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
        
        String[] titulos = {"Id","Rol ID","Rol","Usuario","Documento","# Documento","Dirección","Teléfono","Email","Clave","Estado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);
        
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
    
    //metodo para el Login
    public String login(String email, String clave){
        String resp = "0";
        Usuario usu = this.DATOS.login(email, this.encriptar(clave));
        if(usu!=null){
            if(usu.isCondicion()){
                Variables.usuarioId=usu.getIdusuario();
                Variables.rolId=usu.getIdrol();
                Variables.rolNombre=usu.getRolnombre();
                Variables.usuarioNombre=usu.getNombre();
                Variables.usuarioEmail=usu.getEmail();
                resp = "1";
            }else{
                resp = "2";
            }
        }
        return resp;
    }
    
    //metodo para poder encriptar contraseña
    private static String encriptar(String valor){
        MessageDigest md;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} 
	catch (NoSuchAlgorithmException e) {		
		return null;
	}
	    
	byte[] hash = md.digest(valor.getBytes());
	StringBuilder sb = new StringBuilder();
	    
	for(byte b : hash) {        
		sb.append(String.format("%02x", b));
	}
	    
	return sb.toString();
    }
    
    //metodo para seleccionar un Rol
    public DefaultComboBoxModel seleccionar(){
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Rol> lista = new ArrayList();
        lista = DATOSROL.seleccionar();
        for(Rol item : lista){
            items.addElement(new Rol(item.getIdrol(), item.getNombre()));
        }
        return items;
    }
    
    //metodo paar insetar datos de usarios
    public String insertar(int RolId, String nombre, String tipo_documento,String documento, String direccion, String telefono, String email, 
            String clave){
        if(DATOS.existe(email)){
            return "El registro de Usuario ya existe";
        }else{
            obj.setIdrol(RolId);
            obj.setNombre(nombre);
            obj.setTipo_documento(tipo_documento);
            obj.setDocumento(documento);
            obj.setDireccion(direccion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            obj.setClave(this.encriptar(clave));
            if (DATOS.insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
    //metodo para actualizar datos de usuario
    public String actualizar(int id, int RolId, String nombre, String tipo_documento,String documento, String direccion, String telefono, 
            String email, String emailAnt, String clave){
        if(email.equals(emailAnt)){
            obj.setIdusuario(id);
            obj.setIdrol(id);
            obj.setNombre(nombre);
            obj.setTipo_documento(tipo_documento);
            obj.setDocumento(documento);
            obj.setDireccion(direccion);
            obj.setTelefono(telefono);
            obj.setEmail(email);
            
            String encriptado;
            if(clave.length()==64){
                encriptado=clave;
            }else{
                encriptado=this.encriptar(clave);
            }
            obj.setClave(encriptado);
            if(DATOS.actualizar(obj)){
                return "OK";
            }else{
                return "ERROR al actualizar ususario";
            }
        }else{
            if(DATOS.existe(email)){
                return "El registro existe";
            }else{
                obj.setIdusuario(id);
                obj.setIdrol(id);
                obj.setNombre(nombre);
                obj.setTipo_documento(tipo_documento);
                obj.setDocumento(documento);
                obj.setDireccion(direccion);
                obj.setTelefono(telefono);
                obj.setEmail(email);
                
                String encriptado;
                if(clave.length() == 64){
                    encriptado = clave;
                }else{
                    encriptado=this.encriptar(clave);
                }
                obj.setClave(encriptado);
                
                if(DATOS.actualizar(obj)){
                    return "OK";
                }else{
                    return "ERROR en la actualización de Usuario";
                }
            }
        }
    }
    
    public String desactivar(int id){
        if (DATOS.desactivar(id)){
            return "OK";
        }else{
            return "No se puede desactivar el registro";
        }
    }
    
    public String activar(int id){
        if (DATOS.activar(id)){
            return "OK";
        }else{
            return "No se puede activar el registro";
        }
    }
    
    //metodo del total de usuarios registrados
    public int total(){
        return DATOS.total();
    }
    
    //metodo para el totalMostrados
    public int totalMostrados(){
        return this.registroMostrados;
    }
}
