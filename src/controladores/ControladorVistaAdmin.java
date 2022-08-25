/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import estructuras.Cola;
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
import modelos.productoFueraStockDAO;
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
    productoFueraStockDAO daoFueraStock = new productoFueraStockDAO();
    
    nodoProducto p = new nodoProducto();
    vistaAdmin vAdmin = new vistaAdmin();
    Inventario inventario = new Inventario();
    Cliente clientes = new Cliente();
    NuevoProducto formNuevoProducto = new NuevoProducto();
    ModificarProducto formEditarProd = new ModificarProducto();

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modelo1 = new DefaultTableModel();
    DefaultTableModel modelo2 = new DefaultTableModel();

    listaEnlazadaProducto listaSimpleProducto = (listaEnlazadaProducto) daoProducto.Listar();
    listaEnlazadaCliente listaSimpleCliente = (listaEnlazadaCliente) daoCliente.Listar();
    
    Cola colaInventarioFueraStock = daoFueraStock.Listar();
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
        this.inventario.btnEliminados.addActionListener(this);
        
        //Vista NuevoProducto
        this.formNuevoProducto.btnAgregar.addActionListener(this);
        //Vista ModificarProducto
        this.formEditarProd.btnActualizar.addActionListener(this);

        this.inventario.btnEliminados.addActionListener(this);
        this.inventario.btnModificarFueraStock.addActionListener(this);
        recorrerLista();
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
            formNuevoProducto.txtNom.setText("");
            formNuevoProducto.txtPrecio.setText("");
            formNuevoProducto.spinnerCantidad.setValue(0);
        }
        
        if(e.getSource() == inventario.btnEditar){
            int fila = inventario.tablaInventario.getSelectedRow();
            if(fila == -1){ //Si no se selecciona ninguna fila
                JOptionPane.showMessageDialog(inventario, "Debe seleccionar un producto");
                inventario.getVentanaModificarProducto().setVisible(false);
            } else{ //Si se selecciona una fila
                inventario.getVentanaModificarProducto().setVisible(true);
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
            inventario.getVentanaModificarProducto().setVisible(false);
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

        if(e.getSource() == inventario.btnEliminados){
            System.out.println("HOLI");
            mostrarCola(inventario.tablaFueraStock);
            limpiarTabla(modelo2, inventario.tablaFueraStock);
            mostrarCola(inventario.tablaFueraStock);
        }
        
        if(e.getSource() == inventario.btnModificarFueraStock){
            int fila = inventario.tablaFueraStock.getSelectedRow();
            if(fila == -1){ //Si no se selecciona ninguna fila
                JOptionPane.showMessageDialog(inventario, "Debe seleccionar un producto");
                inventario.getVentanaModificarProducto().setVisible(false);
            } else{ //Si se selecciona una fila
                inventario.getVentanaModificarProducto().setVisible(true);
                //Se obtiene atributos del producto seleccionado
                int id= Integer.parseInt(inventario.tablaFueraStock.getValueAt(fila,0).toString());
                String nom = (String)inventario.tablaFueraStock.getValueAt(fila, 1);
                String cate = (String)inventario.tablaFueraStock.getValueAt(fila,2);
                int precio = (int) inventario.tablaFueraStock.getValueAt(fila,3);
                int cantidad = (int) inventario.tablaFueraStock.getValueAt(fila,4);
                //Se envian los atributos al formulario de edicion
                formEditarProd.txtId.setText(""+id);
                formEditarProd.txtNom.setText(""+nom);
                formEditarProd.comboBoxCategoria.setSelectedItem(cate);
                formEditarProd.txtPrecio.setText(""+precio);
                formEditarProd.spinnerCantidad.setValue(cantidad);
            }
        }
        
    }
    
   /**
    * Toma la tabla y la llena con los datos de la lista enlazada
    * @param tabla la tabla donde se mostraran los datos
    */
    /**
     * It takes a JTable as a parameter, then it creates a DefaultTableModel, then it gets the list of
     * products from the database, then it creates an array of objects, then it loops through the list
     * of products and adds each product to the table
     * 
     * @param tabla The table where the data will be displayed
     */
    public void listarProductos(JTable tabla){
        //recorrerLista();
        modelo = (DefaultTableModel)tabla.getModel();
        //Se obtiene la lista de productos
        listaSimpleProducto = (listaEnlazadaProducto) daoProducto.Listar();
        //nodoProducto aux = listaSimpleProducto.getLista();
        Object[]object = new Object[5];
        //Se recorre la lista de productos
        // The above code is adding the products to the table.
        for(int i = 0; i<listaSimpleProducto.contarNodos(); i++){
            // 
            if(listaSimpleProducto.buscarNodoPos(i).getCantidad() == 0 ){
                
                daoProducto.delete(listaSimpleProducto.buscarNodoPos(i).getId());
            }else{
                //Se obtiene el producto en la posicion i
            object[0] = listaSimpleProducto.buscarNodoPos(i).getId(); 
            object[1] = listaSimpleProducto.buscarNodoPos(i).getNombre();
            object[2] = listaSimpleProducto.buscarNodoPos(i).getCategoria();
            object[3] = listaSimpleProducto.buscarNodoPos(i).getPrecio();
            object[4] = listaSimpleProducto.buscarNodoPos(i).getCantidad();
            //Se agrega el producto a la tabla
            modelo.addRow(object);
            }
            
        }
    }
    
    /**
    * Toma la tabla y la llena con los datos de la lista enlazada
    * @param tabla la tabla donde se mostraran los datos
    */
    public void listarClientes(JTable tabla){
        modelo1 = (DefaultTableModel)tabla.getModel();
        //Se obtiene la lista de clientes
        listaSimpleCliente = (listaEnlazadaCliente) daoCliente.Listar();
        Object[]object = new Object[4];
        //Se recorre la lista de productos
        for(int i = 0; i<listaSimpleCliente.contarNodos(); i++){
            //Se obtiene el producto en la posicion i
            object[0] = listaSimpleCliente.buscarNodo(i).getId(); 
            object[1] = listaSimpleCliente.buscarNodo(i).getNombre();
            object[2] = listaSimpleCliente.buscarNodo(i).getApellido();
            object[3] = listaSimpleCliente.buscarNodo(i).getPagoTotal();
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
        if(formNuevoProducto.txtNom.getText().isBlank() == true || formNuevoProducto.txtPrecio.getText().isBlank() == true ||
                (int) formNuevoProducto.spinnerCantidad.getValue() == 0){
            JOptionPane.showMessageDialog(inventario, "Error. Campos incorrectos");
        } else{
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
        System.out.println("PERFECT "+ cantidad);
        nodoProducto aux = daoProducto.buscar(id); //sera igual a null cuando no este en producto, es decir cuando este fuera stock
        nodoProducto aux2 = daoFueraStock.buscar(id); //sera igual al nodo encontrado cuando este fuera stock
        System.out.println("the cure "+ aux2.getNombre()+aux.getNombre()+aux.getCantidad());
        if(aux.getNombre() != null){
            if(cantidad == 0){
                daoProducto.delete(aux.getId());
                daoFueraStock.agregar(p);
                System.out.println("caso 1");
                 JOptionPane.showMessageDialog(inventario, "Producto trasladado a Inventario Fuera de Stock");
            } else{
                System.out.println("caso 2");
                int verificador = daoProducto.actualizar(p);
                if(verificador == 1){
                    JOptionPane.showMessageDialog(inventario, "Producto actualizado con exito");
                }else{
                    JOptionPane.showMessageDialog(inventario, "Error");
                }
            }
            
        }else if(aux2.getNombre() != null){ //fuera de stock
            System.out.println("twitter");
            if(cantidad > 0){
                System.out.println("jelou spotify");
                daoFueraStock.delete(id);
                daoProducto.agregar(p);
                JOptionPane.showMessageDialog(inventario, "Producto trasladado a Inventario");
            }else{
                System.out.println("caso 3");
                int verificador = daoFueraStock.actualizar(p);
                if(verificador == 1){
                    JOptionPane.showMessageDialog(inventario, "Producto actualizado con exito");
                }else{
                    JOptionPane.showMessageDialog(inventario, "Error");
                }
            }

        }else{
                JOptionPane.showMessageDialog(inventario, "Error. El producto no existe");
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
    
    /**
     * A function that allows you to go through a list of products and add the products that are out of
     * stock to a queue.
     */
    public void recorrerLista(){
        //Se obtiene la lista de productos
        listaSimpleProducto =  daoProducto.Listar();
        nodoProducto aux = listaSimpleProducto.getLista();
        for(int i = 0; i<listaSimpleProducto.contarNodos(); i++){
            if(aux.getCantidad() == 0 ){
                colaInventarioFueraStock.copiarDatos(aux);
                System.out.println("agregando a cola : "+ aux.getNombre());
                
                            int id = aux.getId();
            String nom = aux.getNombre();
            String cate = aux.getCategoria();
            int precio = aux.getPrecio();
            int cantidad = aux.getCantidad();
            //envio
            p.setId(id);
            p.setNombre(nom);
            p.setCategoria(cate);
            p.setPrecio(precio);
            p.setCantidad(cantidad);
            int verificador = daoFueraStock.agregar(p);
            if(verificador == 1){
                System.out.println("agregado");
            }else{
                JOptionPane.showMessageDialog(inventario, "Error");
            }
            }
            aux = aux.getSiguiente();
        }
        
        nodoProducto auxCola = colaInventarioFueraStock.getCabecera();
        for(int i = 0; i<colaInventarioFueraStock.getCantidadNodos(); i++){
            if(auxCola.getCantidad() > 0){
                daoFueraStock.delete(auxCola.getId());
                daoProducto.agregar(auxCola);
                auxCola = auxCola.getSiguiente();
            }
        }
    }

    /**
     * It takes a table as a parameter and then it creates a new model for the table, then it creates a
     * new node and a new object array, then it goes through the list of products and adds them to the
     * table
     * 
     * @param tabla is the table where the data will be displayed
     */
    public void mostrarCola(JTable tabla){
        Cola colaInventarioFueraStock = daoFueraStock.Listar();
        System.out.println("DIOSITO AYUDA");
        modelo2 = (DefaultTableModel) tabla.getModel();
        nodoProducto aux = colaInventarioFueraStock.getCabecera();
        Object[]object = new Object[5];
        //Se recorre la lista de productos
        colaInventarioFueraStock.mostrarCola();
        for(int i = 0; i<colaInventarioFueraStock.getCantidadNodos(); i++){
            //Se obtiene el producto en la posicion i
            System.out.println("AQUI ESTA LA COLA CARAJO"+ aux.getNombre());
            
            object[0] = aux.getId(); 
            object[1] = aux.getNombre();
            object[2] = aux.getCategoria();
            object[3] = aux.getPrecio();
            object[4] = aux.getCantidad();
            //Se agrega el producto a la tabla
            modelo2.addRow(object);
            
            aux = aux.getSiguiente();
        }
    }
}
