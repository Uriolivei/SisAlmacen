package Datos;

import DataBase.Conexion;
import Entidades.Clientes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientesDAO {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public ClientesDAO(){
        CON=Conexion.getInstancia();
    }
    
    public List<Clientes> listar() {
        List<Clientes> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM clientes");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Clientes(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getBoolean(8)));
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

        public boolean insertar(Clientes obj) {
        resp=false;
           try {
            ps=CON.conectar().prepareStatement("INSERT INTO clientes(nombre_cliente,tipo_documento,documento,tipo_cliente,telefono,direccion,"
                    + "condicion) VALUES(?,?,?,?,?,?,1)");
            ps.setString(1, obj.getNombre_cliente());
            ps.setString(2, obj.getTipo_documento());
            ps.setString(3, obj.getDocumento());
            ps.setString(4, obj.getTipo_cliente());
            ps.setString(5, obj.getTelefono());
            ps.setString(6, obj.getDireccion());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al registrar cliente " + e.getMessage());
        }finally{
               ps=null;
               CON.desconectar();
           }
           return resp;
    }
        
    public boolean actualizar(Clientes obj) {
        resp=false;
           try {
            ps=CON.conectar().prepareStatement("UPDATE clientes SET nombre_cliente=?, tipo_documento=?,documento=?,tipo_cliente=?,telefono=?,"
                    + "direccion=? WHERE idcliente=?");
            ps.setString(1, obj.getNombre_cliente());
            ps.setString(2, obj.getTipo_documento());
            ps.setString(3, obj.getDocumento());
            ps.setString(4, obj.getTipo_cliente());
            ps.setString(5, obj.getTelefono());
            ps.setString(6, obj.getDireccion());
            ps.setInt(7, obj.getIdcliente());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al actualizar cliente " + e.getMessage());
        }finally{
               ps=null;
               CON.desconectar();
           }
           return resp;
    }
    
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE clientes SET condicion=0 WHERE idcliente=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar Cliente" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean activar(int id) {
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE clientes SET condicion=1 WHERE idcliente=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar Cliente" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }
    
    public boolean existe(String texto) {
        resp = false;
        try{
            ps=CON.conectar().prepareStatement("SELECT documento FROM clientes WHERE documento=?");
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
    
    public int total(){
        int totalRegistros=0;
        try {
            ps=CON.conectar().prepareStatement("SELECT COUNT(idcliente) FROM clientes");
            rs=ps.executeQuery();
            while(rs.next()){
                totalRegistros=rs.getInt("COUNT(idcliente)");
            }
            ps.close();
            rs.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se puede cargar el total de clientes" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalRegistros;
    }
}
