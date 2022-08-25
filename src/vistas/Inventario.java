/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas;



/**
 *
 * @author EMBAJADA DE ESPAÑA
 */
public class Inventario extends javax.swing.JPanel {
    /**
     * Creates new form Inventario
     */
    NuevoProducto ventanaNuevoProducto = new NuevoProducto(); 
    ModificarProducto ventanaModificarProducto = new ModificarProducto(); 
    
    
    public Inventario() {
        
        
        initComponents();
        panelFueraStock.setVisible(false);
        panelInventario.setVisible(true);
    }

    public NuevoProducto getVentanaNuevoProducto() {
        return ventanaNuevoProducto;
    }

    public ModificarProducto getVentanaModificarProducto() {
        return ventanaModificarProducto;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInventario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnEliminados = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelFueraStock = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaFueraStock = new javax.swing.JTable();
        btnModificarFueraStock = new javax.swing.JButton();
        btnVolverInventario = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(650, 540));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInventario.setBackground(new java.awt.Color(255, 255, 255));
        panelInventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        panelInventario.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 90, 582, 386));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        jLabel1.setText("Inventario");
        panelInventario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnEditar.setText("Modificar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panelInventario.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 50, -1, -1));

        btnEliminados.setText("Fuera de stock");
        btnEliminados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminadosActionPerformed(evt);
            }
        });
        panelInventario.add(btnEliminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 482, 122, -1));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        panelInventario.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 482, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelInventario.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 482, -1, -1));

        add(panelInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 510));

        panelFueraStock.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        jLabel2.setText("Inventario");

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setText("Productos fuera de Stock");

        tablaFueraStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Producto", "Categoría", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaFueraStock);

        btnModificarFueraStock.setText("Modificar");

        btnVolverInventario.setText("Inventario");
        btnVolverInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverInventarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFueraStockLayout = new javax.swing.GroupLayout(panelFueraStock);
        panelFueraStock.setLayout(panelFueraStockLayout);
        panelFueraStockLayout.setHorizontalGroup(
            panelFueraStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFueraStockLayout.createSequentialGroup()
                .addGroup(panelFueraStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFueraStockLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelFueraStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFueraStockLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelFueraStockLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnModificarFueraStock))))
                    .addGroup(panelFueraStockLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelFueraStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFueraStockLayout.createSequentialGroup()
                                .addComponent(btnVolverInventario)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelFueraStockLayout.setVerticalGroup(
            panelFueraStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFueraStockLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFueraStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnModificarFueraStock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolverInventario)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        add(panelFueraStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 510));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
         
        ventanaNuevoProducto.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
         
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminadosActionPerformed
        // TODO add your handling code here:
        panelInventario.setVisible(false);
        panelFueraStock.setVisible(true);
    }//GEN-LAST:event_btnEliminadosActionPerformed

    private void btnVolverInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverInventarioActionPerformed
        // TODO add your handling code here:
        panelFueraStock.setVisible(false);
        panelInventario.setVisible(true);
    }//GEN-LAST:event_btnVolverInventarioActionPerformed

    //controladorProducto c = new controladorProducto(this,nuevoProducto, modificarProducto);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnEliminados;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnModificarFueraStock;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JButton btnVolverInventario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JPanel panelFueraStock;
    public javax.swing.JPanel panelInventario;
    public javax.swing.JTable tablaFueraStock;
    public javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables

}
