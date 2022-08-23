package estructuras;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Nodo producto
 * @author Milena
 */

/**
 * NodoProducto es una clase que contiene los atributos de un producto, y un puntero al siguiente producto
 */
public class nodoProducto{
    int id;
    String nombre;
    String categoria;
    int precio;
    int cantidad;
    nodoProducto siguiente;
    nodoProducto anterior;
    //Constructor
    
    public nodoProducto(int id, String nombre, String categoria, int precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.siguiente = null;
    }

    public nodoProducto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public nodoProducto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nodoProducto siguiente) {
        this.siguiente = siguiente;
    }
    
    public nodoProducto getAnterior() {
        return this.anterior;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}