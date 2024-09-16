package DataBase;

import javax.swing.JOptionPane;

public class MainConectar {
    
    public static void main(String[] args) {
        Conexion conexion = Conexion.getInstancia();
        conexion.conectar();
        if(conexion.cadena!=null){
            JOptionPane.showMessageDialog(null, "Conectado");
        }else{
            System.out.println("Desconectado");
        }
    }
    
    //metodo
    /*public int auto_increment(){
        //variables
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion db = new Conexion();
        
        return 0;
        
    }*/
    
    
}
