/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Clibro;
import Controlador.Conexion;
import Modelo.Alta_libro;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MUÑOZ
 */
public class prestamo_libro extends javax.swing.JInternalFrame {
String  nombre_libro, clasificacion, autor, edicion, isbn;
    int cod_libro, consulta2;
    String   consulta;
    
    Clibro clibro = new Clibro();
    Alta_libro libros = new Alta_libro();
    /**
     * Creates new form prestamo_libro
     */
    public prestamo_libro() {
        initComponents();
        txtnom_libro.setEnabled(false);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        diseñoCuadro1 = new Vista.DiseñoCuadro();
        diseñoCuadro2 = new Vista.DiseñoCuadro();
        diseñoCuadro3 = new Vista.DiseñoCuadro();
        diseñoCuadro4 = new Vista.DiseñoCuadro();
        diseñoCuadro5 = new Vista.DiseñoCuadro();
        diseñoCuadro6 = new Vista.DiseñoCuadro();
        jPanel3 = new javax.swing.JPanel();
        txtcodigo = new Vista.DiseñoCuadro();
        txtnom_libro = new Vista.DiseñoCuadro();
        txtclasificacion = new Vista.DiseñoCuadro();
        txtautor = new Vista.DiseñoCuadro();
        txtedicion = new Vista.DiseñoCuadro();
        txtisbn = new Vista.DiseñoCuadro();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        fecha_prestamo = new javax.swing.JLabel();
        fecha_entrega = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBorder(javax.swing.BorderFactory.createTitledBorder("Libros prestados"));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 360, 575, 210));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1032, 12, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos generales del alumno"));

        diseñoCuadro1.setEnabled(false);

        diseñoCuadro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diseñoCuadro2ActionPerformed(evt);
            }
        });

        diseñoCuadro3.setEnabled(false);

        diseñoCuadro4.setEnabled(false);

        diseñoCuadro5.setEnabled(false);

        diseñoCuadro6.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(diseñoCuadro2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(diseñoCuadro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diseñoCuadro3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diseñoCuadro4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diseñoCuadro5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diseñoCuadro6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(diseñoCuadro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diseñoCuadro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diseñoCuadro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diseñoCuadro4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diseñoCuadro5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diseñoCuadro6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 489, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del libro"));

        txtcodigo.setEnabled(false);

        txtnom_libro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnom_libroActionPerformed(evt);
            }
        });

        txtclasificacion.setEnabled(false);

        txtautor.setEnabled(false);

        txtedicion.setEnabled(false);

        txtisbn.setEnabled(false);
        txtisbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtisbnActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtisbn, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(txtedicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtautor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtclasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnom_libro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnom_libro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtclasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtautor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(8, 8, 8)
                .addComponent(txtedicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtisbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

        fecha_prestamo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        getContentPane().add(fecha_prestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 150, 80));

        fecha_entrega.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        getContentPane().add(fecha_entrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 280, 230, 60));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setText("Fecha del prestamo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel6.setText("Fecha de entrega");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 240, -1, -1));

        jLabel1.setText("IMG");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 360, 180));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtisbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtisbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtisbnActionPerformed

    private void txtnom_libroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnom_libroActionPerformed
        // TODO add your handling code here:
         
         
         
         int libro = Integer.valueOf(txtnom_libro.getText());
         txtcodigo.setText(""+libro);
         txtnom_libro.setText("");
         
         consulta2= libro;
         
            try{
                
               libros = clibro.consulta_libro(Conexion.obtener(),consulta2);
               
               
      txtcodigo.setText(""+libros.getId_libro());
               txtnom_libro.setText(libros.getNombre_libro());
                System.out.println(libros.getNombre_libro());
               txtclasificacion.setText(libros.getAutor());
                txtautor.setText(libros.getClasificacion());
                txtedicion.setText(libros.getEdicion());
                txtisbn.setText(libros.getIsbn());
                this.fechas();
                this.bloqueo();
            }catch(SQLException ex){
                System.out.println(""+ex);
               // JOptionPane.showMessageDialog(this,"Codigo de barras existente");
            } catch (ClassNotFoundException ex) {
        Logger.getLogger(Jinternalalta_libro.class.getName()).log(Level.SEVERE, null, ex);
        
      
    }
        
         
    }//GEN-LAST:event_txtnom_libroActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          txtnom_libro.setEnabled(true);
           txtnom_libro.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void diseñoCuadro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diseñoCuadro2ActionPerformed
        // TODO add your handling code here:
         txtnom_libro.setEnabled(true);
         
    }//GEN-LAST:event_diseñoCuadro2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         txtnom_libro.setText("");
         txtcodigo.setText("");
         txtclasificacion.setText("");
         txtautor.setText("");
         txtedicion.setText("");
         txtisbn.setText("");
         fecha_prestamo.setText("");
         fecha_entrega.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
public void fechas(){
Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");//fecha actual de la computadora
         DateFormat fecha2 = new SimpleDateFormat("dd");//dia actual
         DateFormat fecha3 = new SimpleDateFormat("/MM/yyyy");//mes y año 
        fecha_prestamo.setText(""+fecha.format(date));//ingresa la fecha actual
        int  a = Integer.valueOf(""+fecha2.format(date));//realiza un parseo de int a string para tomar el dia
        int c;
              c=  a+8;//suma el dia actual + 8
         fecha_entrega.setText(""+c+fecha3.format(date));//agrega el dia mes y año para la entrega
}

public void bloqueo(){
    txtnom_libro.setEnabled(false);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vista.DiseñoCuadro diseñoCuadro1;
    private Vista.DiseñoCuadro diseñoCuadro2;
    private Vista.DiseñoCuadro diseñoCuadro3;
    private Vista.DiseñoCuadro diseñoCuadro4;
    private Vista.DiseñoCuadro diseñoCuadro5;
    private Vista.DiseñoCuadro diseñoCuadro6;
    private javax.swing.JLabel fecha_entrega;
    private javax.swing.JLabel fecha_prestamo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private Vista.DiseñoCuadro txtautor;
    private Vista.DiseñoCuadro txtclasificacion;
    private Vista.DiseñoCuadro txtcodigo;
    private Vista.DiseñoCuadro txtedicion;
    private Vista.DiseñoCuadro txtisbn;
    private Vista.DiseñoCuadro txtnom_libro;
    // End of variables declaration//GEN-END:variables
}
