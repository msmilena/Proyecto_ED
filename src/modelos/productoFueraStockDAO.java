/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import bd.Conexion;
import estructuras.Cola;
import estructuras.listaEnlazadaProducto;
import estructuras.nodoProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Milena
 */
public class productoFueraStockDAO {
    Conexion conectar= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    /**
     * Retorna una lista de productos de la base de datos
     * @return A list of products.
     */
    public Cola Listar(){
        //listaEnlazadaProducto listaSimple = new listaEnlazadaProducto();
        Cola cola = new Cola();
        String sql = "Select * from productosfuerastock";
        try{
            con = (Connection) conectar.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // Iterando a través del resultset y agregando cada fila a la lista enlazada simple.
            while(rs.next()){
                
                int id = rs.getInt(2);
                String nombre = rs.getString(3);
                String categoria = rs.getString(4);
                int precio = rs.getInt(5);
                int cantidad = rs.getInt(6);
                
                //listaSimple.insertarFinal(id, nombre, precio, cantidad, categoria);
                cola.encolar(id, nombre, precio, cantidad, categoria);
            }
            
            
        } catch(SQLException e){
            System.out.println("error: "+e);
        }
        return cola;
    }
    
    /**
     * Toma un objeto nodoProducto y lo inserta en la base de datos
     * @param p es el objeto que contiene los datos a insertar en la base de datos.
     */
    public int agregar(nodoProducto p){
        String sql = "insert into productosfuerastock(id,nombre,categoria,precio,cantidad) values(?,?,?,?,?)";
                try{
                    con = conectar.conexion();
                    ps = con.prepareStatement(sql);
                    
                    ps.setInt(1, p.getId());
                    ps.setString(2, p.getNombre());
                    ps.setString(3, p.getCategoria());
                    ps.setInt(4, p.getPrecio());
                    ps.setInt(5, p.getCantidad());
                    ps.executeUpdate();
                } catch(SQLException e){
                    System.out.println("error: "+e);
                    return 0;
                }
        return 1;
    }
    
    /**
     * Actualiza la base de datos con los nuevos valores del producto
     * @param p es el objeto que contiene los datos a actualizar
     */
    public int actualizar(nodoProducto p){
        int verificador = 0;
        String sql = "update productosfuerastock set nombre=?, categoria=?, precio=?, cantidad=? where id=?";
        try{
            con = conectar.conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria());
            ps.setInt(3, p.getPrecio());
            ps.setInt(4, p.getCantidad());
            ps.setInt(5, p.getId());
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
    
    /**
     * Borra una fila de la base de datos
     * @param id int
     */
    public void delete(int id){
        String sql = "delete from productosfuerastock where id="+id;
        try{
            con = conectar.conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch(SQLException e){
            System.out.println("error: "+e);
        }
    }
    
    //Metodo para buscar un producto en la base de datos por su nombre
    public nodoProducto buscar(int id){
        
        nodoProducto p = new nodoProducto();
        String sql = "select * from productosfuerastock where id="+id;
        try{
            con = conectar.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("FEURA STOCJ");
                p.setId(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setCategoria(rs.getString(4));
                p.setPrecio(rs.getInt(5));
                p.setCantidad(rs.getInt(6));
            }
        } catch(SQLException e){
            System.out.println("error: "+e);
        }
        return p;
    }
}
