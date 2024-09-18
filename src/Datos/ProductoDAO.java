
package Datos;

import DataBase.Conexion;
import Datos.CrudInterface.ProductoInterface;
import Entidades.Categoria;
import Entidades.Productos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDAO implements ProductoInterface<Productos> {
    //variables
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    //constructor
    public ProductoDAO(){
        CON=Conexion.getInstancia();
    }

    @Override
    public List<Productos> listar(String texto) {
        List<Productos> registros = new ArrayList<>();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM productos WHERE nombre_producto LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Productos(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), 
                        rs.getString(9), rs.getDouble(10), rs.getBoolean(11)));
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
    public boolean insertar(Productos obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Productos obj) {
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //metodo paar la consulta SQL para seleccinar categorias
    public List<Categoria> seleccionar(){
        List<Categoria> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT idcategoria, nombre FROM categorias ORDER BY nombre ASC");
            rs = ps.executeQuery();
            while(rs.next()){
                registros.add(new Categoria(rs.getInt(1),rs.getString(2)));
            }
            ps.close();
            rs.close();
        } catch (Exception yeji) {
            JOptionPane.showMessageDialog(null, "No se puede cargar categorias" + yeji.getMessage());
        }finally{
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
}
