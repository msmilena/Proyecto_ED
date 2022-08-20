/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import estructuras.listaEnlazadaCliente;
import estructuras.listaEnlazadaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.productoDAO;
import estructuras.nodoProducto;
import modelos.clienteDAO;
import vistas.Cliente;
import vistas.Inventario;
import vistas.ModificarProducto;
import vistas.NuevoProducto;
import vistas.vistaAdmin;


/**
 * Es una clase controlador que maneja las acciones de los botones de la vista
 */
public class ControladorVistaAdmin implements ActionListener {
    //Creando nuevas instancias de las clases
    productoDAO daoProducto = new productoDAO();
    clienteDAO daoCliente= new clienteDAO();
    nodoProducto p = new nodoProducto();
    vistaAdmin vAdmin = new vistaAdmin();
    Inventario inventario = new Inventario();
    Cliente clientes = new Cliente();
    NuevoProducto formNuevoProducto = new NuevoProducto();
    ModificarProducto formEditarProd = new ModificarProducto();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo1 = new DefaultTableModel();
    //un constructor que inicializa las variables y agrega los listeners de eventos a los botones
    public ControladorVistaAdmin(vistaAdmin vistaAdmin){
        this.vAdmin = vistaAdmin;
        this.clientes = vistaAdmin.getvCliente();
        this.inventario = vistaAdmin.getvInventario();
        this.formNuevoProducto = vistaAdmin.getvInventario().getVentanaNuevoProducto();
        this.formEditarProd = vistaAdmin.getvInventario().getVentanaModificarProducto();
        
        //Vista admin
        this.vAdmin.btnCliente.addActionListener(this);
        
        //Inventario
        this.inventario.btnEditar.addActionListener(this);
        this.inventario.btnEliminar.addActionListener(this);
        
        
        //Vista NuevoProducto
        this.formNuevoProducto.btnAgregar.addActionListener(this);
        //Vista ModificarProducto
        this.formEditarProd.btnActualizar.addActionListener(this);
        
        listarProductos(inventario.tablaInventario);
        limpiarTabla(modelo, inventario.tablaInventario);
        listarProductos(inventario.tablaInventario);
        
        
    }
      
    @Override
    // Un metodo que es llamado cuando se realiza una accion en un componente que ha sido registrado con
    // un ActionListener
    public void actionPerformed(ActionEvent e) {
     
        if(e.getSource() == formNuevoProducto.btnAgregar){            
            agregar();
            limpiarTabla(modelo, inventario.tablaInventario);
            listarProductos(inventario.tablaInventario);
        }
        
        if(e.getSource() == inventario.btnEditar){
            int fila = inventario.tablaInventario.getSelectedRow();
            if(fila == -1){ //Si no se selecciona ninguna fila
                JOptionPane.showMessageDialog(inventario, "Debe seleccionar un producto");
            } else{ //Si se selecciona una fila
                //Se obtiene atributos del producto seleccionado
                int id= Integer.parseInt(inventario.tablaInventario.getValueAt(fila,0).toString());
                String nom = (String)inventario.tablaInventario.getValueAt(fila, 1);
                String cate = (String)inventario.tablaInventario.getValueAt(fila,2);
                int precio = (int) inventario.tablaInventario.getValueAt(fila,3);
                int cantidad = (int) inventario.tablaInventario.getValueAt(fila,4);
                //Se envian los atributos al formulario de edicion
                formEditarProd.txtId.setText(""+id);
                formEditarProd.txtNom.setText(""+nom);
                formEditarProd.comboBoxCategoria.setSelectedItem(cate);
                formEditarProd.txtPrecio.setText(""+precio);
                formEditarProd.spinnerCantidad.setValue(cantidad);
            }
        }
        
        if(e.getSource() == formEditarProd.btnActualizar){
            actualizar();
            limpiarTabla(modelo, inventario.tablaInventario);
            listarProductos(inventario.tablaInventario);
        }
        
        if(e.getSource() == inventario.btnEliminar){
            delete();
            limpiarTabla(modelo, inventario.tablaInventario);
            listarProductos(inventario.tablaInventario);
        }
        
        if(e.getSource() == vAdmin.btnCliente){
            listarClientes(clientes.tablaClientes);
            limpiarTabla(modelo1, clientes.tablaClientes);
            listarClientes(clientes.tablaClientes);
        }
        
    }
    
   /**
    * Toma la tabla y la llena con los datos de la lista enlazada
    * @param tabla la tabla donde se mostraran los datos
    */
    public void listarProductos(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        //Se obtiene la lista de productos
        listaEnlazadaProducto listaSimple = (listaEnlazadaProducto) daoProducto.Listar();
        Object[]object = new Object[5];
        //Se recorre la lista de productos
        for(int i = 0; i<listaSimple.contarNodos(); i++){
            //Se obtiene el producto en la posicion i
            object[0] = listaSimple.buscarNodo(i).getId(); 
            object[1] = listaSimple.buscarNodo(i).getNombre();
            object[2] = listaSimple.buscarNodo(i).getCategoria();
            object[3] = listaSimple.buscarNodo(i).getPrecio();
            object[4] = listaSimple.buscarNodo(i).getCantidad();
            //Se agrega el producto a la tabla
            modelo.addRow(object);
        }
    }
    
    /**
    * Toma la tabla y la llena con los datos de la lista enlazada
    * @param tabla la tabla donde se mostraran los datos
    */
    public void listarClientes(JTable tabla){
        modelo1 = (DefaultTableModel)tabla.getModel();
        //Se obtiene la lista de productos
        listaEnlazadaCliente listaSimple = (listaEnlazadaCliente) daoCliente.Listar();
        Object[]object = new Object[4];
        //Se recorre la lista de productos
        for(int i = 0; i<listaSimple.contarNodos(); i++){
            //Se obtiene el producto en la posicion i
            object[0] = listaSimple.buscarNodo(i).getId(); 
            object[1] = listaSimple.buscarNodo(i).getNombre();
            object[2] = listaSimple.buscarNodo(i).getApellido();
            object[3] = listaSimple.buscarNodo(i).getPagoTotal();
            //Se agrega el producto a la tabla
            modelo1.addRow(object);
            System.out.println("esta agregando fila");
        }
    }
    
    /**
     * Agrega un producto a la base de datos
     * Tomar los valores del formulario y los envia a la clase DAO para que sean agregados a la base de datos
     */
    public void agregar(){
        String nom = formNuevoProducto.txtNom.getText();
        String cate = (String) formNuevoProducto.comboBoxCategoria.getSelectedItem();
        int precio = Integer.parseInt(formNuevoProducto.txtPrecio.getText());
        int cantidad = (int) formNuevoProducto.spinnerCantidad.getValue();
        //envio
        p.setNombre(nom);
        p.setCategoria(cate);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        int verificador = daoProducto.agregar(p);
        
        if(verificador == 1){
            JOptionPane.showMessageDialog(inventario, "Producto agregado con exito");
        }else{
            JOptionPane.showMessageDialog(inventario, "Error");
        }
    }
    
    /**
     * Tomar los valores de los campos de texto y los envia a la clase nodoProducto para que sean actualizados
     * Luego envia el nodoProducto a la clase DAO para que sea actualizado en la base de datos
     */
    public void actualizar(){
        int id = Integer.parseInt(formEditarProd.txtId.getText());
        String nom = formEditarProd.txtNom.getText();
        String cate = (String) formEditarProd.comboBoxCategoria.getSelectedItem();
        int precio = Integer.parseInt(formEditarProd.txtPrecio.getText());
        int cantidad = (int) formEditarProd.spinnerCantidad.getValue();
        //envio a nodoProducto para que sea actualizado
        p.setId(id);
        p.setNombre(nom);
        p.setCategoria(cate);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        //envio a DAO para que sea actualizado en la base de datos
        int verificador = daoProducto.actualizar(p);
        
        if(verificador == 1){
            JOptionPane.showMessageDialog(inventario, "Producto actualizado con exito");
        }else{
            JOptionPane.showMessageDialog(inventario, "Error");
        }
    }
    
    /**
     * Borra un producto de la base de datos
     */
    public void delete(){
        //Se obtiene el id del producto seleccionado
        int fila = inventario.tablaInventario.getSelectedRow();
            
        if(fila == -1){
            JOptionPane.showMessageDialog(inventario, "Debe seleccionar un producto");
        } else{
            int id = Integer.parseInt((String) inventario.tablaInventario.getValueAt(fila, 0).toString());
            daoProducto.delete(id);
            JOptionPane.showMessageDialog(inventario, "Producto eliminado");
        }
    }
    
    void limpiarTabla(DefaultTableModel model, JTable tabla){
        for(int i = 0; i<tabla.getRowCount(); i++){
            model.removeRow(i);
            i = i - 1;
        }
    }
}
