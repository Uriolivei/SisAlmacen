
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
import javax.swing.event.DocumentEvent;

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
        ps = CON.conectar().prepareStatement(
            "SELECT p.idproducto AS Idproducto, " +
            "ca.nombre AS categoria, " +
            "p.nombre_producto, p.descripcion_producto, " +
            "p.imagen_producto, p.codigo_producto, " +
            "p.marca_producto, p.cantidad_producto, " +
            "p.fecha_vencimiento, p.precio_compra, " +
            "p.condicion " +
            "FROM productos p " +
            "INNER JOIN categorias ca ON p.categoria_id = ca.idcategoria " +
            "WHERE p.nombre_producto LIKE ?"
        );
        ps.setString(1, "%" + texto + "%");
        rs = ps.executeQuery();
        
        // Procesar los resultados
        while (rs.next()) {
            // Verifica los índices aquí
            registros.add(new Productos(
                rs.getInt("Idproducto"),  // Cambia el índice por el alias de la columna
                rs.getString("categoria"), 
                rs.getString("nombre_producto"), 
                rs.getString("descripcion_producto"), 
                rs.getString("imagen_producto"), 
                rs.getString("codigo_producto"), 
                rs.getString("marca_producto"), 
                rs.getInt("cantidad_producto"), 
                rs.getString("fecha_vencimiento"), 
                rs.getDouble("precio_compra"), 
                rs.getBoolean("condicion")
            ));
        }
        
        // Cerrar recursos
        ps.close();
        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se puede mostrar datos en la tabla " + e.getMessage());
    } finally {
        ps = null;
        rs = null;
        CON.desconectar();
    }
    return registros;
}



    @Override
    public boolean insertar(Productos obj) {
        resp=false;
           try {
            ps=CON.conectar().prepareStatement("INSERT INTO productos(categoria_id,nombre_producto,descripcion_producto,codigo_producto"
                    + "marca_producto,cantidad_producto,fecha_vencimiento,precio_compra,condicion) VALUES(?,?,1)");
            ps.setString(1, obj.getCategoria_id());
            ps.setString(2, obj.getNombre_producto());
            ps.setString(3, obj.getDescripcion_producto());
            ps.setString(4, obj.getCodigo_producto());
            ps.setString(5, obj.getMarca_producto());
            ps.setInt(6, obj.getCantidad_producto());
            ps.setString(7, obj.getFecha_vencimiento());
            ps.setDouble(8, obj.getPrecio_compra());
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
    public boolean actualizar(Productos obj) {
        resp=false;
        try {
           ps=CON.conectar().prepareStatement("UPDATE productos SET categoria_id= ?, nombre_producto=?, descripcion_producto=?, codigo_producto=?,"
                   + "marca_producto=?, cantidad_producto=?, fecha_vencimiento=?, precio_compra=?, condicion=? WHERE idproducto=?");
           ps.setString(1, obj.getCategoria_id());
           ps.setString(2, obj.getNombre_producto());
           ps.setString(3, obj.getDescripcion_producto());
           ps.setString(4, obj.getCodigo_producto());
           ps.setString(5, obj.getMarca_producto());
           ps.setInt(6, obj.getCantidad_producto());
           ps.setString(7, obj.getFecha_vencimiento());
           ps.setDouble(8, obj.getPrecio_compra());
           ps.setInt(9, obj.getIdproducto());
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
        resp = false;
        try {
            ps=CON.conectar().prepareStatement("UPDATE productos SET condicion=0 WHERE idproducto=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar producto" + yeji.getMessage());
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
            ps=CON.conectar().prepareStatement("UPDATE productos SET condicion=1 WHERE idproducto=?");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException yeji) {
            JOptionPane.showMessageDialog(null, "No se pudo activar producto" + yeji.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
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
            ps=CON.conectar().prepareStatement("SELECT nombre_producto FROM productos WHERE nombre_producto=?");
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
