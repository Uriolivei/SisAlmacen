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
    
    public RolDAO(){
        CON=Conexion.getInstancia();
    }
    
    //Metodo para listar en tabla los roles
    public List<Rol> listar(){
        List<Rol> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM roles");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Rol(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            ps.close();
            rs.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null,"No se puede Visualizar los roles de la BD" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
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
