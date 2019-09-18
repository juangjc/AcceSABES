/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Calumno;
import Controlador.Conexion;
import Modelo.Alumno;
import Modelo.Carrera;
import Modelo.Status;
import Modelo.Vconsultaalumnos;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kalas
 */
public class JInternalConsulta extends javax.swing.JInternalFrame {

   
    String matricula, nombre, apellido, telefono, tarjeta,email;
    int idcarrera, idstatus;
    DefaultTableModel modelo = new DefaultTableModel();
    List<Vconsultaalumnos> alumnos = new ArrayList<>();
    Calumno calumno = new Calumno();
    Object[] columnas = new Object[7];
    Object[] datos = new Object[7];
    DefaultComboBoxModel modelocarrera, modelostatus;
    Alumno alumno;
    Blob bytesImagen;
    byte[] bytesLeidos;
    ImageIcon imageicon;

    /**
     * Creates new form JInternalConsulta
     */
    public JInternalConsulta() {
        initComponents();
        columnas[0] = "Matricula";
        columnas[1] = "Nombre";
        columnas[2] = "Apellido";
        columnas[3] = "Telefono";
        columnas[4] = "Correo electronico";
        columnas[5] = "Carrera";
        columnas[6] = "Status";
        modelo.setColumnIdentifiers(columnas);
        tblalumnos.setModel(modelo);
        modelocarrera = new DefaultComboBoxModel();
        modelostatus = new DefaultComboBoxModel();
        llena_combo_carrera();
        llena_combo_status();
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
        tblalumnos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        cbbcarrera = new javax.swing.JComboBox<>();
        cbbstatus = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();
        txtmatricula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblmatricula = new javax.swing.JLabel();
        btnactualizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();

        setClosable(true);
        setTitle("consulta de alumnos");

        tblalumnos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblalumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblalumnosMouseClicked(evt);
            }
        });
        tblalumnos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblalumnosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblalumnos);

        jLabel1.setText("Matricula: ");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido:");

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        jLabel4.setText("Telefono:");

        cbbcarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Carrera:");

        jLabel6.setText("Status:");

        lblimagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtmatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmatriculaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmatriculaKeyTyped(evt);
            }
        });

        jLabel8.setText("Buscar:");

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        jLabel7.setText("Email:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnactualizar)
                                .addGap(130, 130, 130))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtmatricula))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                                    .addComponent(lblmatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnactualizar)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnombre))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtapellido))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(395, 395, 395))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatriculaKeyTyped

    }//GEN-LAST:event_txtmatriculaKeyTyped

    private void txtmatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatriculaKeyReleased
        
        
        modelo.setRowCount(0);
        matricula = txtmatricula.getText();
        try {
            alumnos = calumno.consulta(Conexion.obtener(), matricula);
            for (int i = 0; i <= alumnos.size() - 1; i++) {
                datos[0] = alumnos.get(i).getMatricula();
                datos[1] = alumnos.get(i).getNombre();
                datos[2] = alumnos.get(i).getApellidos();
                datos[3] = alumnos.get(i).getTelefono();
                datos[4]=alumnos.get(i).getEmail();
                datos[5] = alumnos.get(i).getCarrera();
                datos[6] = alumnos.get(i).getStatus();
                modelo.addRow(datos);

            }
            tblalumnos.setModel(modelo);

        } catch (SQLException ex) {
            Logger.getLogger(JInternalConsulta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JInternalConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        


    }//GEN-LAST:event_txtmatriculaKeyReleased

    private void tblalumnosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblalumnosKeyPressed

    }//GEN-LAST:event_tblalumnosKeyPressed

    private void tblalumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblalumnosMouseClicked
        
            int y = tblalumnos.getSelectedRow();
            int x = tblalumnos.getSelectedColumn();
           
            matricula=(String) tblalumnos.getValueAt(y, 0);
            try {
            alumno=calumno.recuperaralumno(Conexion.obtener(), matricula);
            lblmatricula.setText(alumno.getMatricula());
            txtnombre.setText(alumno.getNombre());
            txtapellido.setText(alumno.getApellido());
            txttelefono.setText(alumno.getTelefono());
            txtemail.setText(alumno.getEmail());
            cbbcarrera.setSelectedIndex(alumno.getIdcarrera()-1);
            cbbstatus.setSelectedIndex(alumno.getIdstatus()-1);
            bytesImagen = alumno.getFotografia();
            bytesLeidos = bytesImagen.getBytes(1, (int) bytesImagen.length());
            BufferedImage img = null;
            try {
                                img = ImageIO.read(new ByteArrayInputStream(bytesLeidos));
                            } catch (IOException ex) {
                                Logger.getLogger(JInternalacceso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            imageicon = new ImageIcon(img);
                            Icon icono = new ImageIcon(imageicon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                            lblimagen.setIcon(imageicon);
            
        } catch (SQLException ex) {
            Logger.getLogger(JInternalConsulta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JInternalConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
      


    }//GEN-LAST:event_tblalumnosMouseClicked

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        matricula = lblmatricula.getText();
        nombre = txtnombre.getText();
        apellido = txtapellido.getText();
        telefono = txttelefono.getText();
        email=txtemail.getText();
        Carrera cr = (Carrera) this.cbbcarrera.getSelectedItem();
        idcarrera = cr.getIdcarrera();
        Status st = (Status) this.cbbstatus.getSelectedItem();
        idstatus = st.getIdstatus();
        alumno = new Alumno(matricula, nombre, apellido, telefono,email, idcarrera, idstatus);
        try {
            calumno.actualizaralumno(Conexion.obtener(), alumno);
            JOptionPane.showMessageDialog(this,
        "Registro actualizado",
        "Actualización de registro",
        JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(JInternalConsulta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JInternalConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c) == false) {
        }else{
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    public void llena_combo_carrera() { // static para poder llamarlo desde el otro frame o JDialog
        Carrera carrera;

        try {
            modelocarrera.removeAllElements(); // eliminamos lo elementos
            Connection conexion = Conexion.obtener();
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM carrera");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                carrera = new Carrera(rs.getInt(1), rs.getString(2));
                modelocarrera.addElement(carrera);

            }
            cbbcarrera.setModel(modelocarrera); // seteamos el modelo y se cargan los datos
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void llena_combo_status() { // static para poder llamarlo desde el otro frame o JDialog
        Status status;

        try {
            modelostatus.removeAllElements(); // eliminamos lo elementos
            Connection conexion = Conexion.obtener();
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM status");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                status = new Status(rs.getInt(1), rs.getString(2));
                modelostatus.addElement(status);

            }
            cbbstatus.setModel(modelostatus); // seteamos el modelo y se cargan los datos
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JComboBox<String> cbbcarrera;
    private javax.swing.JComboBox<String> cbbstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lblmatricula;
    private javax.swing.JTable tblalumnos;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmatricula;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
