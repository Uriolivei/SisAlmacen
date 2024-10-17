package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DataBase.Conexion;
import Entidades.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class RolDAO {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public RolDAO(){
        CON=Conexion.getInstancia();
    }
    
    //Metodo para listar en tabla los roles
    public List<Rol> listar() {
        List<Rol> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM roles");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Rol(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

    
    public boolean insertar(Rol obj) {
        resp=false;
           try {
            ps=CON.conectar().prepareStatement("INSERT INTO roles(nombre,descripcion,condicion) VALUES(?,?,1)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al registrar rol " + e.getMessage());
        }finally{
               ps=null;
               CON.desconectar();
           }
           return resp;
    }
    
    public boolean actualizar(Rol obj) {
        resp=false;
        try {
           ps=CON.conectar().prepareStatement("UPDATE roles SET nombre=?, descripcion=? WHERE idrol=?");
           ps.setString(1, obj.getNombre());
           ps.setString(2, obj.getDescripcion());
           ps.setInt(3, obj.getIdrol());
           if(ps.executeUpdate()>0){
               resp = true;
           }
           ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se puede actualizar los datos" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE roles SET condicion=0 WHERE idrol=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar rol" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean activar(int id) {
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE roles SET condicion=1 WHERE idrol=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar rol" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }
    
    //metodo par seleccionar rol
    public List<Rol> seleccionar(){
        List<Rol> registros = new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT idrol,nombre FROM roles ORDER BY nombre asc");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Rol(rs.getInt(1), rs.getString(2)));
            }
            ps.close();
            rs.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null,"No se puede seleccionar Rol" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }
    
     public boolean existe(String texto) {
        resp = false;
        try{
            ps=CON.conectar().prepareStatement("SELECT nombre FROM roles WHERE nombre=?");
            ps.setString(1,texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }
            rs.close();
            ps.close();
        }catch(SQLException yeji){
            JOptionPane.showMessageDialog(null,"No se puede validar datos" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }
    
    //metodo para saber el total de roles
    public int total(){
        int totalRegistros=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT COUNT(idrol) FROM roles");
            rs=ps.executeQuery();
            while(rs.next()){
                totalRegistros=rs.getInt("COUNT(idrol)");
            }
            ps.close();
            rs.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se puede cargar el total de roles" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalRegistros;
    }
}
