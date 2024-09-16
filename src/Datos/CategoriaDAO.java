package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.CategoriaInterface;
import Entidades.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO implements CategoriaInterface<Categoria> {
    //variables
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    //constructor
    public CategoriaDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Categoria> listar(String texto) {
        List<Categoria> registros = new ArrayList<>();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM categorias WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " No se puede mostrar datos en la tabla " + e.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Categoria obj) {
        resp=false;
           try {
            ps=CON.conectar().prepareStatement("INSERT INTO categorias(nombre,descripcion,condicion) VALUES(?,?,1)");
            ps.setString(1, obj.getNombre());
            ps.setString(1, obj.getDescripcion());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al registrar categoria " + e.getMessage());
        }finally{
               ps=null;
               CON.desconectar();
           }
           return resp;
    }

    @Override
    public boolean actualizar(Categoria obj) {
        resp=false;
        try {
           ps=CON.conectar().prepareCall("UPDATE categorias SET nombere=?, descripcion=? WHERE idcategoria=?");
           ps.setString(1, obj.getNombre());
           ps.setString(2, obj.getDescripcion());
           ps.setInt(3, obj.getIdcategoria());
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
        int totalRegistros = 0;
        try {
          ps=CON.conectar().prepareStatement("SELECT COUNT(idcategoria) FROM categorias");
          rs=ps.executeQuery();
          while(rs.next()){
              totalRegistros=rs.getInt("COUNT(idcategoria)");
          }
          rs.close();
          ps.close();
        } catch (Exception yeji) {
            JOptionPane.showMessageDialog(null,"No se puede obtener el total de categorias" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp = false;
        try{
            ps=CON.conectar().prepareStatement("SELECT nombre FROM categorias WHERE nombre=?");
            ps.setString(1,texto);
            rs=ps.executeQuery();
            rs.last();
            if(rs.getRow()>0){
                resp=true;
            }
            rs.close();
            ps.close();
        }catch(Exception yeji){
            JOptionPane.showMessageDialog(null,"No se puede validar datos" + yeji.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }
    
    
}
