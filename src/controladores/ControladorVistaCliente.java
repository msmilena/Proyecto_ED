/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import estructuras.Pila;
import estructuras.listaCircularDobleProductos;
import estructuras.listaEnlazadaProducto;
import estructuras.nodoCliente;
import estructuras.nodoProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.productoDAO;
import modelos.clienteDAO;
import vistas.Carrito;
import vistas.Inventario;
import vistas.Productos;
import vistas.vistaCliente;
import vistas.RegistroUsuario;

/**
 *
 * @author Milena
 */
public class ControladorVistaCliente implements ActionListener{
    int cont=0;
    boolean compra = false;
    
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
    
    
    listaEnlazadaProducto listaSimple = (listaEnlazadaProducto) daoProducto.Listar();
    listaCircularDobleProductos circularDoble = new listaCircularDobleProductos();
    nodoProducto actual = pasarSimpleaCircular(listaSimple, circularDoble);
    
    public ControladorVistaCliente(vistaCliente vClientes) {
        this.ventanaCliente = vClientes;
        this.productos = vClientes.getVentanaProductos();
        this.carrito = vClientes.getVentanaCarrito();
        this.ventanaRegistro = vClientes.getVentanaCarrito().getVentanaRegistroUsuario();
        
        this.productos.btnVistaGaleria.addActionListener(this);
        this.productos.btnAnterior.addActionListener(this);
        this.productos.btnSiguiente.addActionListener(this);
        
        this.ventanaCliente.btnRegresar.addActionListener(this);
        this.ventanaCliente.btnCarrito.addActionListener(this);
        this.ventanaRegistro.btnPagar.addActionListener(this);
        this.productos.btnAgregarCarrito.addActionListener(this);
        this.carrito.btnComprar.addActionListener(this);
        this.productos.tablaInventario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                seleccionarProducto();
            }
        });
        
        productos.panelGaleria.setVisible(false);
        productos.spinnerCantidad.setValue(0);
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
            if(pilaCarrito.peek() == null){
                JOptionPane.showMessageDialog(carrito, "No hay productos en el carrito!");
            }else{
                 ventanaRegistro.setVisible(true);
                 mostrarMonto(obtenerPago());
            }
            
        }
        if(e.getSource() == ventanaRegistro.btnPagar){
            
            if(registroCliente(obtenerPago()) == 0){
                ventanaRegistro.setVisible(false);
            }else{
                limpiarTabla(modelo1, carrito.tablaCarrito);
            
            ventanaRegistro.setVisible(false);
            int n = pilaCarrito.contar();
            for(int i=0;i<n;i++){
                pilaCarrito.pop();
            }
            //limpiando campos
            carrito.txtPagoTotal.setText("0");
            ventanaRegistro.txtNom.setText("");
            ventanaRegistro.txtApellido.setText("");
            ventanaRegistro.txtMonto.setText("");
            
            }
        }
        
        if(e.getSource() == ventanaCliente.btnRegresar){
            actualizarCantidad();
            compra = false;
        }
        
        if(e.getSource() == productos.btnVistaGaleria){
            if(cont == 0){
                productos.panelTabla.setVisible(false);
                productos.panelGaleria.setVisible(true);
                productos.btnVistaGaleria.setText("Vista Tabla");
                vistaGaleria(actual);
                cont=1;
            }else{
                productos.panelTabla.setVisible(true);
                productos.panelGaleria.setVisible(false);
                productos.btnVistaGaleria.setText("Vista Galería");
                productos.spinnerCantidad.setValue(0);
                productos.txtNombreProducto.setText("");
                cont =0;
            }
        }

        if(e.getSource() == productos.btnAnterior){
            actual = moverAnterior(actual);
            vistaGaleria(actual);
        }
        
        if(e.getSource() == productos.btnSiguiente){
            actual = moverSiguiente(actual);
            vistaGaleria(actual);
        }
        
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        listaSimple = (listaEnlazadaProducto) daoProducto.Listar();
        
        Object[]object = new Object[5];
        for(int i = 0; i<listaSimple.contarNodos(); i++){
            object[0] = listaSimple.buscarNodoPos(i).getId();
            object[1] = listaSimple.buscarNodoPos(i).getNombre();
            object[2] = listaSimple.buscarNodoPos(i).getCategoria();
            object[3] = listaSimple.buscarNodoPos(i).getPrecio();
            object[4] = listaSimple.buscarNodoPos(i).getCantidad();
            modelo.addRow(object);
        }
        
    }
    
    public void agregarCarrito(JTable tabla){
        int id=0, precio=0, cantidadTotal=0, cantidadComprar=0;
        String nom="", cate="";
        int fila = productos.tablaInventario.getSelectedRow();
        
        if(productos.txtNombreProducto.getText().isEmpty() == true  && fila == -1){
            JOptionPane.showMessageDialog(productos, "Debe seleccionar un producto");
            return;
        } else{
            if(fila == -1){
                id = Integer.parseInt(productos.txtId.getText());
                nom = productos.txtNomProducto.getText();
                cate = productos.txtCategoria.getText();
                precio = Integer.parseInt(productos.txtPrecio.getText());
                cantidadTotal = Integer.parseInt(productos.txtCantidad.getText());
                cantidadComprar = (int) productos.spinnerCantidad.getValue();
            }else{
                id= Integer.parseInt(productos.tablaInventario.getValueAt(fila,0).toString());
                nom = (String)productos.tablaInventario.getValueAt(fila, 1);
                cate = (String)productos.tablaInventario.getValueAt(fila,2);
                precio = (int) productos.tablaInventario.getValueAt(fila,3);
                cantidadTotal = (int) productos.tablaInventario.getValueAt(fila,4);
                cantidadComprar = (int) productos.spinnerCantidad.getValue();
            }
            
            //comprobar con la base de datos si la cantidad de productos es mayor a la que se quiere comprar
            if(cantidadComprar == 0){
                JOptionPane.showMessageDialog(productos, "No se puede comprar 0 productos");
                return;
            }
            if(cantidadComprar <= cantidadTotal){
                //agregando a la pila
                pilaCarrito.push(id, nom, precio, cantidadComprar, cate);
                p.setId(id);
                p.setNombre(nom);
                p.setCategoria(cate);
                p.setPrecio(precio);
                p.setCantidad(cantidadTotal-cantidadComprar);
                System.out.println("cantidad total: "+cantidadTotal);
                System.out.println("cantidad comprar: "+cantidadComprar);
                
                //actualizando la cantidad de productos en la base de datos
                daoProducto.actualizar(p);
                
                JOptionPane.showMessageDialog(productos, "Producto agregado con exito");
                //actualizando la tabla
                limpiarTabla(modelo, productos.tablaInventario);
                listar(productos.tablaInventario);
                productos.spinnerCantidad.setValue(0);
                productos.txtNombreProducto.setText("");
            } else{
                JOptionPane.showMessageDialog(productos, "No hay suficientes productos");
            }
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
            pagoTotal = pagoTotal + temp.getPrecio()*temp.getCantidad();
            temp = temp.getSiguiente();
        }
        return pagoTotal;
    }
    
    public int registroCliente(int monto){
        int verificador=0;
        if(ventanaRegistro.txtNom.getText().isBlank() == false  && ventanaRegistro.txtApellido.getText().isBlank() == false){
            String nom = ventanaRegistro.txtNom.getText();
            String apellido = ventanaRegistro.txtApellido.getText();
            //envio
            cliente.setNombre(nom);
            cliente.setApellido(apellido);
            cliente.setPagoTotal(monto);
            verificador = daoCliente.agregar(cliente);
            if(verificador == 1){
                JOptionPane.showMessageDialog(carrito, "Cliente agregado con exito");
                boolean compra = true;
            }else{
                JOptionPane.showMessageDialog(carrito, "Error");
            }
        }else{
            JOptionPane.showMessageDialog(carrito, "campos vacios");
        }
        return verificador;
    }
    
    public void seleccionarProducto(){
        productos.spinnerCantidad.setValue(1);
        int fila = productos.tablaInventario.getSelectedRow();
        if(fila == -1){
            System.out.println("vacio "+fila);
            productos.txtNombreProducto.setText("");
        }else{
            System.out.println("lleno "+fila);
            productos.txtNombreProducto.setText((String)productos.tablaInventario.getValueAt(fila, 1));
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
    
    public void actualizarCantidad(){
        listaSimple = (listaEnlazadaProducto) daoProducto.Listar();
        if(compra == false){
            if(pilaCarrito.estaVacio() == false){
                int n = pilaCarrito.contar();
                nodoProducto aux = new nodoProducto();
                aux = pilaCarrito.peek();
                for(int i=0;i<n;i++){
                    p.setId(aux.getId());
                    p.setNombre(aux.getNombre());
                    p.setPrecio(aux.getPrecio());
                    System.out.println("auxilio "+ p.getNombre());
                    p.setCantidad(aux.getCantidad()+listaSimple.buscarNodoId(aux.getId()).getCantidad());
                    System.out.println("cantidad actualizada "+p.getCantidad());
                    daoProducto.actualizar(p);
                    aux = aux.getSiguiente();
                }
            }
        }
    }
    
    public void vistaGaleria(nodoProducto actual){
        productos.txtId.setText(""+actual.getId());
        productos.txtNomProducto.setText(actual.getNombre());
        productos.txtCantidad.setText(""+actual.getCantidad());
        productos.txtCategoria.setText(actual.getCategoria());
        productos.txtPrecio.setText(""+actual.getPrecio());
        productos.txtNombreProducto.setText(actual.getNombre());
        productos.spinnerCantidad.setValue(1);
    }
    
    public nodoProducto moverAnterior(nodoProducto actual){
        System.out.println("anterior");
        actual = actual.getAnterior();
        return actual;
    }
    
    public nodoProducto moverSiguiente(nodoProducto actual){
        System.out.println("Siguiente");
        actual = actual.getSiguiente();
        return actual;
    }
    
    public nodoProducto pasarSimpleaCircular(listaEnlazadaProducto listaSimple, listaCircularDobleProductos circularDoble){
        //listaSimple = (listaEnlazadaProducto) daoProducto.Listar();
        //circularDoble = new listaCircularDobleProductos();
        for(int i = 0; i<listaSimple.contarNodos(); i++){
            circularDoble.copiarDatos(listaSimple.buscarNodoPos(i));
        }
        nodoProducto actual = circularDoble.getListaCircularDoble();
        return actual;
    }
}
