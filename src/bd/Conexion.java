/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

/**
 *
 * @author Milena
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class Conexion {
    Connection conectar=null;
    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_market", "root", "");
            System.out.println("Conexion aceptada");
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("mensaje de error"+e);
            JOptionPane.showMessageDialog(null, "No se pudo conectar");
        }
        return conectar;
    }
    
}
