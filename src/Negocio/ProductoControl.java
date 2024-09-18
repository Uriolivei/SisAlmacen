package Negocio;

import Datos.CategoriaDAO;
import Entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class ProductoControl {
    private final CategoriaDAO DATOSCAT;
    
    public ProductoControl(){
        this.DATOSCAT = new CategoriaDAO();
    }
    
    //
        public DefaultComboBoxModel seleccionar(){
            DefaultComboBoxModel items = new DefaultComboBoxModel();
            List<Categoria> lista = new ArrayList();
            lista = DATOSCAT.seleccionar();
            for(Categoria item:lista){
                items.addElement(new Categoria(item.getIdcategoria(),item.getNombre()));
            }
            return items;
        }
}
