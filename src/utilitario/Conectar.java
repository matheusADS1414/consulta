package utilitario;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Conectar {
    private static final String usuario = "root";
    private static final String senha = "abreu";
    private static final String url = "jdbc:mysql://localhost/consultorio";
    public static Connection getConectar(){
    Connection con = null;
    try{
    Class.forName("com.mysql.jbdc.Driver");
    DriverManager.getConnection(url, usuario, senha);
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados!"+ex.getMessage());
    }
    return con;
    } 
}
