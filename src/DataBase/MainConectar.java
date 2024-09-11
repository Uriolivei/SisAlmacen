package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainConectar {
    
    //metodo
    /*public int auto_increment(){
        //variables
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion db = new Conexion();
        
        return 0;
        
    }*/
    
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion = Conexion.getInstancia();
        conexion.conectar();
        if(conexion.cadena!= null){
            System.out.println("Conectado");
        }else{
            System.out.println("Desconectado");    
        }
    }
    
    
}
