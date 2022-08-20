/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import estructuras.listaEnlazadaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.productoDAO;
import estructuras.nodoProducto;
import vistas.Inventario;
import vistas.ModificarProducto;
import vistas.NuevoProducto;


/**
 *
 * @author Milena
 */
public class controladorInventario implements ActionListener {
    productoDAO dao = new productoDAO();
    nodoProducto p = new nodoProducto();
    
    Inventario inventario = new Inventario();
    NuevoProducto formNuevoProducto = new NuevoProducto();
    ModificarProducto formEditarProd = new ModificarProducto();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public controladorInventario(Inventario vistaInventario){
        this.inventario = vistaInventario;
        this.formNuevoProducto = vistaInventario.getVentanaNuevoProducto();
        this.formEditarProd = vistaInventario.getVentanaModificarProducto();
        
        //Inventario
        this.inventario.btnEditar.addActionListener(this);
        this.inventario.btnEliminar.addActionListener(this);
        
        
        //Vista NuevoProducto
        this.formNuevoProducto.btnAgregar.addActionListener(this);
        //Vista ModificarProducto
        this.formEditarProd.btnActualizar.addActionListener(this);
        
        listar(inventario.tablaInventario);
        limpiarTabla();
        listar(inventario.tablaInventario);
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
     
        if(e.getSource() == formNuevoProducto.btnAgregar){            
            agregar();
            limpiarTabla();
            listar(inventario.tablaInventario);
        }
        
        if(e.getSource() == inventario.btnEditar){
            int fila = inventario.tablaInventario.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(inventario, "Debe seleccionar un producto");
            } else{
                int id= Integer.parseInt(inventario.tablaInventario.getValueAt(fila,0).toString());
                String nom = (String)inventario.tablaInventario.getValueAt(fila, 1);
                String cate = (String)inventario.tablaInventario.getValueAt(fila,2);
                int precio = (int) inventario.tablaInventario.getValueAt(fila,3);
                int cantidad = (int) inventario.tablaInventario.getValueAt(fila,4);
                
                formEditarProd.txtId.setText(""+id);
                formEditarProd.txtNom.setText(""+nom);
                formEditarProd.comboBoxCategoria.setSelectedItem(cate);
                formEditarProd.txtPrecio.setText(""+precio);
                formEditarProd.spinnerCantidad.setValue(cantidad);
            }
        }
        
        if(e.getSource() == formEditarProd.btnActualizar){
            actualizar();
            limpiarTabla();
            listar(inventario.tablaInventario);
        }
        
        if(e.getSource() == inventario.btnEliminar){
            delete();
            limpiarTabla();
            listar(inventario.tablaInventario);
        }
        
        
    }
    
    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        listaEnlazadaProducto listaSimple = (listaEnlazadaProducto) dao.Listar();
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
        int verificador = dao.agregar(p);
        
        if(verificador == 1){
            JOptionPane.showMessageDialog(inventario, "Producto agregado con exito");
        }else{
            JOptionPane.showMessageDialog(inventario, "Error");
        }
    }
    
    public void actualizar(){
        int id = Integer.parseInt(formEditarProd.txtId.getText());
        String nom = formEditarProd.txtNom.getText();
        String cate = (String) formEditarProd.comboBoxCategoria.getSelectedItem();
        int precio = Integer.parseInt(formEditarProd.txtPrecio.getText());
        int cantidad = (int) formEditarProd.spinnerCantidad.getValue();
       
        p.setId(id);
        p.setNombre(nom);
        p.setCategoria(cate);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        
        int verificador = dao.actualizar(p);
        
        if(verificador == 1){
            JOptionPane.showMessageDialog(inventario, "Producto actualizado con exito");
        }else{
            JOptionPane.showMessageDialog(inventario, "Error");
        }
    }
    
    public void delete(){
        int fila = inventario.tablaInventario.getSelectedRow();
            
        if(fila == -1){
            JOptionPane.showMessageDialog(inventario, "Debe seleccionar un producto");
        } else{
            int id = Integer.parseInt((String) inventario.tablaInventario.getValueAt(fila, 0).toString());
            dao.delete(id);
            JOptionPane.showMessageDialog(inventario, "Producto eliminado");
        }
    }
    
    
    void limpiarTabla(){
        for(int i = 0; i<inventario.tablaInventario.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
