package DataBase;

//libreria paar la conexion a MySql
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;//para ocultar errores de conexion a la DB

public class Conexion {
    //variables
    private final String DRIVER="com.mysql.jdbc.Driver";
    private final String URL="jdbc:mysql://localhost:3306/";
    private final String DB="dbalmacen";
    private final String USER="root";
    private final String PASSWORD="";
    
    public Connection cadena;//variable que importa la libreria Sql
    public static Conexion instancia;//variable de instancia a la clase
    
    //constructor

    public Conexion() {
        this.cadena = null;
    }
    
    //metodo conectar a la red
    public Connection conectar(){
        try{
            Class.forName(DRIVER);  
            this.cadena=DriverManager.getConnection(URL+DB,USER,PASSWORD);
            System.out.println("Conexion establecida");
        }catch(ClassNotFoundException | SQLException yeji){
            JOptionPane.showMessageDialog(null,"ERROR de conexión a la BD" + yeji.getMessage());
            System.out.println("ERROR de conexion a la BD");
            System.exit(0);
        }
        return this.cadena;
    }
    
    //metodo para desconectar la base de datos
    public void desconectar(){
        try{
            this.cadena.close();
        }catch(SQLException yeji){
            JOptionPane.showMessageDialog(null,"No se pude cerrar la consulta Statement" + yeji.getMessage());
        }
    }
    
    //metodo para instanciar o conectar de forma automatica la conexion
    public synchronized static Conexion getInstancia(){
        if(instancia==null){
            instancia = new Conexion();
        }
        return instancia;
    }
    
}
