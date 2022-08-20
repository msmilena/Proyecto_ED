/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import estructuras.Pila;
import estructuras.listaEnlazadaProducto;
import estructuras.nodoCliente;
import estructuras.nodoProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.productoDAO;
import modelos.clienteDAO;
import vistas.Carrito;
import vistas.Productos;
import vistas.vistaCliente;
import vistas.RegistroUsuario;

/**
 *
 * @author Milena
 */
public class controladorProductos implements ActionListener{
    
    productoDAO daoProducto = new productoDAO();
    clienteDAO daoCliente = new clienteDAO();
    nodoProducto p = new nodoProducto();
    nodoCliente cliente = new nodoCliente();
    
    vistaCliente ventanaCliente = new vistaCliente();
    Productos productos = new Productos();
    Carrito carrito = new Carrito();
    RegistroUsuario ventanaRegistro = new RegistroUsuario();
    
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo1 = new DefaultTableModel();

    Pila pilaCarrito = new Pila();
    
    public controladorProductos(vistaCliente vClientes) {
        this.ventanaCliente = vClientes;
        this.productos = vClientes.getVentanaProductos();
        this.carrito = vClientes.getVentanaCarrito();
        this.ventanaRegistro = vClientes.getVentanaCarrito().getVentanaRegistroUsuario();
        
        this.ventanaCliente.btnCarrito.addActionListener(this);
        this.ventanaRegistro.btnPagar.addActionListener(this);
        this.productos.btnAgregarCarrito.addActionListener(this);
        this.carrito.btnComprar.addActionListener(this);
        listar(productos.tablaInventario);
        limpiarTabla(modelo, productos.tablaInventario);
        listar(productos.tablaInventario);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == productos.btnAgregarCarrito){
            agregarCarrito(productos.tablaInventario);
        }
        
        if(e.getSource() == ventanaCliente.btnCarrito){
            mostrarCarrito(carrito.tablaCarrito);
            limpiarTabla(modelo1, carrito.tablaCarrito);
            mostrarCarrito(carrito.tablaCarrito);
            carrito.txtPagoTotal.setText(""+obtenerPago());
        }
        
        if(e.getSource() == carrito.btnComprar){
            mostrarMonto(obtenerPago());
        }
        if(e.getSource() == ventanaRegistro.btnPagar){
            registroCliente(obtenerPago());
            limpiarTabla(modelo1, carrito.tablaCarrito);
            
            ventanaRegistro.setVisible(false);
            int n = pilaCarrito.contar();
            for(int i=0;i<n;i++){
                pilaCarrito.pop();
            }
        }
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        listaEnlazadaProducto listaSimple = (listaEnlazadaProducto) daoProducto.Listar();
        Object[]object = new Object[5];
        for(int i = 0; i<listaSimple.contarNodos(); i++){
            object[0] = listaSimple.buscarNodo(i).getId();
            object[1] = listaSimple.buscarNodo(i).getNombre();
            object[2] = listaSimple.buscarNodo(i).getCategoria();
            object[3] = listaSimple.buscarNodo(i).getPrecio();
            object[4] = listaSimple.buscarNodo(i).getCantidad();
            modelo.addRow(object);
        }
    }
    
    public void agregarCarrito(JTable tabla){
        int fila = productos.tablaInventario.getSelectedRow();
        System.out.println("a");
        if(fila == -1){
            JOptionPane.showMessageDialog(productos, "Debe seleccionar un producto");
            return;
        } else{
            JOptionPane.showMessageDialog(productos, "Producto agregado con exito");
            int id= Integer.parseInt(productos.tablaInventario.getValueAt(fila,0).toString());
            String nom = (String)productos.tablaInventario.getValueAt(fila, 1);
            String cate = (String)productos.tablaInventario.getValueAt(fila,2);
            int precio = (int) productos.tablaInventario.getValueAt(fila,3);
            int cantidad = (int) productos.tablaInventario.getValueAt(fila,4);
            
            pilaCarrito.push(id, nom, precio, cantidad, cate);
            pilaCarrito.display();
        }
    }
    
    public void mostrarCarrito(JTable tabla){
        if(pilaCarrito == null){
            limpiarTabla(modelo1, tabla);
        }
        modelo1 = (DefaultTableModel)tabla.getModel();
        Object[]object = new Object[5];
        nodoProducto temp = pilaCarrito.peek();
        while(temp != null){
            object[0] = temp.getId();
            object[1] = temp.getNombre();
            object[2] = temp.getCategoria();
            object[3] = temp.getPrecio();
            object[4] = temp.getCantidad();
            modelo1.addRow(object);
            temp = temp.getSiguiente();
        }
        
        
    }
    
    public int obtenerPago(){
        nodoProducto temp = pilaCarrito.peek();
        int pagoTotal = 0;
        while(temp != null){
            pagoTotal = pagoTotal + temp.getPrecio();
            temp = temp.getSiguiente();
        }
        return pagoTotal;
    }
    
    public void registroCliente(int monto){
        int verificador=0;
        if(ventanaRegistro.txtNom != null && ventanaRegistro.txtApellido != null){
            String nom = ventanaRegistro.txtNom.getText();
            String apellido = ventanaRegistro.txtApellido.getText();
            //envio
            cliente.setNombre(nom);
            cliente.setApellido(apellido);
            cliente.setPagoTotal(monto);
            verificador = daoCliente.agregar(cliente);
            
        }
        if(verificador == 1){
                JOptionPane.showMessageDialog(carrito, "Cliente agregado con exito");
            }else{
                JOptionPane.showMessageDialog(carrito, "Error");
            }
    }
    
    public void mostrarMonto(int monto){
        ventanaRegistro.txtMonto.setText(""+monto);
    }
    
    void limpiarTabla(DefaultTableModel model, JTable tabla){
        for(int i = 0; i<tabla.getRowCount(); i++){
            model.removeRow(i);
            i = i - 1;
        }
    }
}
