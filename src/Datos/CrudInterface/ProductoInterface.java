
package Datos.CrudInterface;

import java.util.List;

public interface ProductoInterface<T> {
    //métodos para el crud
   public List<T> listar(String texto,int totalPorPagina,int numPagina);
   public boolean insertar(T obj);
   public boolean actualizar(T obj);
   public boolean desactivar(int id);
   public boolean activar(int id);
   public int total();
   public boolean existe(String texto);
}
