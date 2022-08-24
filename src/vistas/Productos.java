/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas;

import java.awt.BorderLayout;
import javax.swing.text.AbstractDocument.Content;

/**
 *
 * @author EMBAJADA DE ESPAÑA
 */
public class Productos extends javax.swing.JPanel {
    /**
     * Creates new form Inventario
     */
    public Productos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnAgregarCarrito = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        spinnerCantidad = new javax.swing.JSpinner();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        btnVistaGaleria = new javax.swing.JButton();
        panelGaleria = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNomProducto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(650, 540));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Categoría");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, 20));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 200, -1));

        btnAgregarCarrito.setText("Añadir al carrito");
        btnAgregarCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCarritoActionPerformed(evt);
            }
        });
        add(btnAgregarCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, -1, -1));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        jLabel2.setText("Productos");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        txtNombreProducto.setEditable(false);
        txtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreProductoActionPerformed(evt);
            }
        });
        add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 120, -1));

        jLabel3.setText("Producto");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 70, -1));

        jLabel4.setText("Llevar");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, -1, -1));

        spinnerCantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        add(spinnerCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 90, -1));

        panelTabla.setBackground(new java.awt.Color(255, 255, 255));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Producto", "Categoría", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaInventario);

        panelTabla.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, 330));

        add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 640, 350));

        btnVistaGaleria.setText("Vista Galería");
        btnVistaGaleria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVistaGaleriaActionPerformed(evt);
            }
        });
        add(btnVistaGaleria, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        panelGaleria.setBackground(new java.awt.Color(255, 255, 255));
        panelGaleria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Producto: ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        txtNomProducto.setEditable(false);
        jPanel2.add(txtNomProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 120, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("  Categoría: ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        txtCategoria.setEditable(false);
        jPanel2.add(txtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 120, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Precio: ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Cantidad: ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        txtPrecio.setEditable(false);
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        jPanel2.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 120, -1));

        txtCantidad.setEditable(false);
        jPanel2.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 120, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText(" Id: ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        txtId.setEditable(false);
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 120, -1));

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel2.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        panelGaleria.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 390, 270));

        add(panelGaleria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 640, 350));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCarritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarCarritoActionPerformed

    private void txtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreProductoActionPerformed

    private void btnVistaGaleriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVistaGaleriaActionPerformed
        // TODO add your handling code here:        
        
    }//GEN-LAST:event_btnVistaGaleriaActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarCarrito;
    public javax.swing.JButton btnAnterior;
    public javax.swing.JButton btnSiguiente;
    public javax.swing.JButton btnVistaGaleria;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelGaleria;
    public javax.swing.JPanel panelTabla;
    public javax.swing.JSpinner spinnerCantidad;
    public javax.swing.JTable tablaInventario;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCategoria;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtNomProducto;
    public javax.swing.JTextField txtNombreProducto;
    public javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
