package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.CrudPaginadoInterface;
import Entidades.Usuario;
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
    
    public UsuarioDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Usuario> listar(String texto, int totalPorPagina, int numPagina) {
        List<Usuario> registros = new ArrayList();
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
        return registros;
    }
    
    //metodo del login
    public Usuario login(String email, String clave){
        Usuario usu = null;
        try {
            ps = CON.conectar().prepareStatement("SELECT u.idusuario,u.idrol,r.nombre AS rol_nombre.u,nombre,u.tipo_documento,u.documento,u.direccion,u.telefono,u.condicion"
                    + "FROM usuarios u INNER JOIN roles r ON u.idrol=r.idrol WHERE u.email=? AND clave=?");
            ps.setString(1, email);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            
            if(rs.first()){
                usu = new Usuario(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Usuario obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean desactivar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean activar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existe(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
