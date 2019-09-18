/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Cbiblioteca;
import Controlador.Clibro;
import Controlador.Conexion;
import Controlador.Cprestamo_biblioteca;
import Modelo.Alta_libro;
import Modelo.Valumnos;
import Modelo.Vprestamo_biblioteca;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author MUÑOZ
 */
public class prestamo_libro extends javax.swing.JInternalFrame {
    Cprestamo_biblioteca Caltas =new Cprestamo_biblioteca();
    Vprestamo_biblioteca prestamo;
    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    String idtarjeta = null;
    Cbiblioteca cbiblioteca= new Cbiblioteca();
    Valumnos valumno= new Valumnos();
String  nombre_libro, clasificacion, autor, edicion, isbn;
    int cod_libro, consulta2;
    String   consulta;
    ImageIcon imageicon;
    Blob bytesImagen;
    
    Clibro clibro = new Clibro();
    Alta_libro libros = new Alta_libro();
    InternalFrameListener listener1 = new InternalFrameListener() {
        @Override
        public void internalFrameOpened(InternalFrameEvent e) {
            System.out.println("opened");
        }

        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
            try {
                System.out.println("framedeac");
                arduino.killArduinoConnection();
            } catch (ArduinoException ex) {
                Logger.getLogger(JInternalacceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void internalFrameClosed(InternalFrameEvent e) {
            try {
                System.out.println("closed");
                arduino.killArduinoConnection();
            } catch (ArduinoException ex) {
                Logger.getLogger(JInternalacceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void internalFrameIconified(InternalFrameEvent e) {
            System.out.println("iconi");
        }

        @Override
        public void internalFrameDeiconified(InternalFrameEvent e) {
            System.out.println("deico");
        }

        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            try {
                //  arduino.arduinoRXTX("COM4", 9600, listener);
                System.out.println("frameacti");
            } catch (Exception ex) {
                Logger.getLogger(JInternalacceso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void internalFrameDeactivated(InternalFrameEvent e) {
            System.out.println("deac");
        }
    };
    private SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {

                if (arduino.isMessageAvailable()) {

                   idtarjeta = arduino.printMessage();
                   try {
                       
                       txtnom_libro.setEnabled(true);
                       borrar();
            valumno=cbiblioteca.consultaralumno(Conexion.obtener(), idtarjeta);
            txtmatricula.setText(valumno.getMatricula());
            txtnombre.setText(valumno.getNombre());
            txtapellido.setText(valumno.getApellido());
            txtemail.setText(valumno.getEmail());
             bytesImagen = valumno.getFotografia();
                            byte[] bytesLeidos = bytesImagen.getBytes(1, (int) bytesImagen.length());
                            BufferedImage img = null;
                            try {
                                img = ImageIO.read(new ByteArrayInputStream(bytesLeidos));
                            } catch (IOException ex) {
                                Logger.getLogger(JInternalacceso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            imageicon = new ImageIcon(img);
                            Icon icono = new ImageIcon(imageicon.getImage().getScaledInstance(184, 152, Image.SCALE_SMOOTH));
                            lblimagen.setIcon(icono);
                            txtnom_libro.requestFocus();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(prestamo_libro.class.getName()).log(Level.SEVERE, null, ex);
        }           catch (SQLException ex) { 
                        Logger.getLogger(prestamo_libro.class.getName()).log(Level.SEVERE, null, ex);
                    }
              System.out.println(idtarjeta);      
                }
            }catch (SerialPortException | ArduinoException ex) {
                ex.printStackTrace();
          } 

        }

    };
    public void consultar(String idtarjeta) throws SQLException{
        try {
            valumno=cbiblioteca.consultaralumno(Conexion.obtener(), idtarjeta);
            txtmatricula.setText(valumno.getMatricula());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(prestamo_libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public prestamo_libro() {
        initComponents();
        addInternalFrameListener(listener1);
        
        try {

            arduino.arduinoRXTX("COM4", 9600, listener);

            //System.out.println(arduino.getInputBytesAvailable());
        } catch (Exception ex) {
            
          Logger.getLogger(prestamo_libro.class.getName()).log(Level.SEVERE, null, ex);

        }
       // txtnom_libro.setEnabled(false);
        
        
    }
    public void borrar(){
        txtcodigo.setText("");
        txtnom_libro.setText("");
        txtclasificacion.setText("");
        txtautor.setText("");
        txtedicion.setText("");
        txtisbn.setText("");
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
        txtmatricula = new Vista.DiseñoCuadro();
        txtnombre = new Vista.DiseñoCuadro();
        txtapellido = new Vista.DiseñoCuadro();
        txtemail = new Vista.DiseñoCuadro();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fecha_prestamo = new javax.swing.JLabel();
        fecha_entrega = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblimagen = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Prestamos de libros");
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

        txtmatricula.setEnabled(false);
        txtmatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmatriculaActionPerformed(evt);
            }
        });

        txtnombre.setEnabled(false);
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        txtapellido.setEnabled(false);

        txtemail.setEnabled(false);

        jLabel2.setText("Matricula:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Apellido:");

        jLabel7.setText("Email:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnombre, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(txtmatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtapellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtemail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 489, 150));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del libro"));

        txtcodigo.setEnabled(false);

        txtnom_libro.setEnabled(false);
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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jLabel8.setText("Codigo:");

        jLabel9.setText("Nombre:");

        jLabel10.setText("Clasificacion:");

        jLabel11.setText("Autor:");

        jLabel12.setText("Edicion:");

        jLabel13.setText("ISBN:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtisbn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtedicion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtautor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtclasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnom_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnom_libro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtclasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtautor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel11))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtedicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtisbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
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

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 210, 180));

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
                this.cajavacia();
            }catch(SQLException ex){
                System.out.println(""+ex);
               // JOptionPane.showMessageDialog(this,"Codigo de barras existente");
            } catch (ClassNotFoundException ex) {
        Logger.getLogger(Jinternalalta_libro.class.getName()).log(Level.SEVERE, null, ex);
        
      
    }
        
         
    }//GEN-LAST:event_txtnom_libroActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.borrar();
          txtnom_libro.setEnabled(true);
           txtnom_libro.setText("");
           txtnom_libro.requestFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
         txtnom_libro.setEnabled(true);
         
    }//GEN-LAST:event_txtnombreActionPerformed

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

    private void txtmatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmatriculaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String  matricula = txtmatricula.getText();
        int id_libro = Integer.valueOf(txtcodigo.getText());
        String fecha_inicio = fecha_prestamo.getText();
        String fecha_final = fecha_entrega.getText();
        
       
            
        prestamo = new Vprestamo_biblioteca(id_libro,matricula, fecha_inicio, fecha_final);
             try{
                
                Caltas.Guardarinfo(Conexion.obtener(), prestamo);
                JOptionPane.showMessageDialog(this, "Guardado");
            }catch(SQLException ex){
                System.out.println(""+ex);
                JOptionPane.showMessageDialog(this,"Codigo de barras existente");
            } catch (ClassNotFoundException ex) {
        Logger.getLogger(Jinternalalta_libro.class.getName()).log(Level.SEVERE, null, ex);
    }
         this.borrar(); 
            
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
public void fechas(){
Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");//fecha actual de la computadora
         DateFormat fecha2 = new SimpleDateFormat("dd");//dia actual
         DateFormat fecha3 = new SimpleDateFormat("yyyy-MM-");//mes y año 
        fecha_prestamo.setText(""+fecha.format(date));//ingresa la fecha actual
        int  a = Integer.valueOf(""+fecha2.format(date));//realiza un parseo de int a string para tomar el dia
        int c;
              c=  a+8;//suma el dia actual + 8
         fecha_entrega.setText(""+fecha3.format(date)+c);//agrega el dia mes y año para la entrega
}

public void bloqueo(){
    txtnom_libro.setEnabled(false);
}
public void cajavacia(){
    int a = Integer.parseInt( txtcodigo.getText());
    int b =0;
    if (a==b) {
        JOptionPane.showMessageDialog(this, "Libro no existente", "",JOptionPane.ERROR_MESSAGE);
        fecha_prestamo.setText("");
        fecha_entrega.setText("");
        txtcodigo.setText("");
        txtnom_libro.setEnabled(true);
        txtnom_libro.requestFocus();
    }
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fecha_entrega;
    private javax.swing.JLabel fecha_prestamo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblimagen;
    private Vista.DiseñoCuadro txtapellido;
    private Vista.DiseñoCuadro txtautor;
    private Vista.DiseñoCuadro txtclasificacion;
    private Vista.DiseñoCuadro txtcodigo;
    private Vista.DiseñoCuadro txtedicion;
    private Vista.DiseñoCuadro txtemail;
    private Vista.DiseñoCuadro txtisbn;
    private Vista.DiseñoCuadro txtmatricula;
    private Vista.DiseñoCuadro txtnom_libro;
    private Vista.DiseñoCuadro txtnombre;
    // End of variables declaration//GEN-END:variables
}
