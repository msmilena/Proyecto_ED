/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import bd.Conexion;
import estructuras.listaEnlazadaCliente;
import estructuras.nodoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Milena
 */
public class clienteDAO {
    Conexion conectar= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public listaEnlazadaCliente Listar(){
        listaEnlazadaCliente listaSimple = new listaEnlazadaCliente();
        String sql = "Select * from clientes";
        try{
            con = (Connection) conectar.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                int monto = rs.getInt(4);
                
                listaSimple.insertarFinal(id, nombre, apellido, monto);
            }
            
            
        } catch(SQLException e){
            System.out.println("error: "+e);
        }
        return listaSimple;
    }
    
    public int agregar(nodoCliente cliente){
        String sql = "insert into clientes(nombre,apellido,pagoTotal) values(?,?,?)";
                try{
                    con = conectar.conexion();
                    ps = con.prepareStatement(sql);
                    
                    ps.setString(1, cliente.getNombre());
                    ps.setString(2, cliente.getApellido());
                    ps.setInt(3, cliente.getPagoTotal());
                    ps.executeUpdate();
                } catch(SQLException e){
                    System.out.println("error: "+e);
                    return 0;
                }
        return 1;
    }
    
    public int actualizar(nodoCliente cliente){
        int verificador = 0;
        String sql = "update clientes set nombre=?, apellido=?, pagoTotal=? where id=?";
        try{
            con = conectar.conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getPagoTotal());
            ps.setInt(5, cliente.getId());
            verificador = ps.executeUpdate();
            if(verificador == 1){
                return 1;
            } else{
                return 0;
            }
        }catch(SQLException e){
            System.out.println("error: "+e);
        }
        return verificador;
    }
    
    public void delete(int id){
        String sql = "delete from clientes where id="+id;
        try{
            con = conectar.conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){
            System.out.println("error: "+e);
        }
    }
}
