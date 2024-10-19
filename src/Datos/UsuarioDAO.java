package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.CrudPaginadoInterface;
import Entidades.Usuario;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class UsuarioDAO implements CrudPaginadoInterface<Usuario>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    private Connection conn;
    
    public UsuarioDAO(){
        CON=Conexion.getInstancia();
        
    }

    @Override
    public List<Usuario> listar(String texto, int totalPorPagina, int numPagina) {
    List<Usuario> registros = new ArrayList<>();
    try {
        ps = CON.conectar().prepareStatement("SELECT u.idusuario, u.idrol, r.nombre AS rol_nombre, u.nombre, u.tipo_documento, "
                + "u.documento, u.direccion, u.telefono, u.email, u.clave, u.imagen, u.condicion "
                + "FROM usuarios u INNER JOIN roles r ON u.idrol = r.idrol "
                + "WHERE u.nombre LIKE ? ORDER BY u.idusuario ASC LIMIT ?, ?");
        
        ps.setString(1, "%" + texto + "%");
        ps.setInt(2, (numPagina - 1) * totalPorPagina); 
        ps.setInt(3, totalPorPagina); 
        rs = ps.executeQuery();
        
        while (rs.next()) {
            registros.add(new Usuario(
                rs.getInt("idusuario"), 
                rs.getInt("idrol"), 
                rs.getString("rol_nombre"),
                rs.getString("nombre"), 
                rs.getString("tipo_documento"), 
                rs.getString("documento"), 
                rs.getString("direccion"), 
                rs.getString("telefono"), // Asegúrate de que el constructor tenga este campo
                rs.getString("email"), 
                rs.getString("clave"),
                rs.getString("imagen"), 
                rs.getBoolean("condicion")
            ));
        }
    } catch (SQLException yeji) {
        JOptionPane.showMessageDialog(null, "No se puede ver la lista de usuarios: " + yeji.getMessage());
    } finally {
        // Cerrar recursos de manera ordenada
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            CON.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
        }
    }
    return registros;
        /*List<Usuario> registros = new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT u.idusuario,u.idrol,r.nombre AS rol_nombre,u.nombre,u.tipo_documento,u.documento,u.direccion,u.email,u.clave,"
                    + "u.condicion FROM usuarios u INNER JOIN roles r ON u.idrol = r.idrol WHERE u.nombre LIKE ? ORDER BY u.idusuario ASC LIMIT ?, ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add(new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10), 
                        rs.getBoolean(11)));
            }
        } catch (Exception yeji) {
            JOptionPane.showMessageDialog(null, "No se puede ver la lista de usuarios" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;*/
    }
    
    //metodo del login
    public Usuario login(String email, String clave){
        Usuario usu = null;
        try {
            ps = CON.conectar().prepareStatement("SELECT u.idusuario,u.idrol,r.nombre AS rol_nombre,u.nombre,u.tipo_documento,u.documento,"
                    + "u.direccion,u.telefono,u.email,u.imagen,u.condicion FROM usuarios u INNER JOIN roles r ON u.idrol=r.idrol "
                    + "WHERE u.email=? AND u.clave=?");
            ps.setString(1, email);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            
            if(rs.first()){
                usu = new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getString(9), rs.getString(10), rs.getBoolean(11));
            }
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se puede acceder" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return usu;
    }
    
        
    @Override
    public boolean insertar(Usuario obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO usuarios(idrol,nombre,tipo_documento,documento,direccion,telefono,email,clave,"
                    + "imagen, condicion) VALUES(?,?,?,?,?,?,?,?,?,1)");
            ps.setInt(1, obj.getIdrol());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipo_documento());
            ps.setString(4, obj.getDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getClave());
            ps.setString(9, obj.getImagen());

            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();

        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se puede registrar usuario: " + yeji.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
}

    @Override
    public boolean actualizar(Usuario obj) {
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE usuarios SET idrol=?, nombre=?,tipo_documento=?,documento=?,direccion=?,telefono=?,"
                    + "email=?,clave=?,imagen=? WHERE idusuario=?");
            ps.setInt(1, obj.getIdrol());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipo_documento());
            ps.setString(4, obj.getDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getClave());
            ps.setString(9, obj.getImagen() );
            ps.setInt(10, obj.getIdusuario());
            
            if(ps.executeUpdate()>0){
                resp=true;
            }
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se puede actualizar usuario" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE usuarios SET condicion=0 WHERE idusuario=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar categoría" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean activar(int id) {
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE usuarios SET condicion=1 WHERE idusuario=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar categoría" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
        int totalRegistros=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT COUNT(idusuario) FROM usuarios");            
            rs=ps.executeQuery();
            
            while(rs.next()){
                totalRegistros=rs.getInt("COUNT(idusuario)");
            }            
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp = false;
        try {
            ps =CON.conectar().prepareStatement("SELECT email FROM usuarios WHERE email=?");
            ps.setString(1, texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp = true;
            }
            ps.close();
            rs.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se encuentra datos" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
         return resp;
    }
}
