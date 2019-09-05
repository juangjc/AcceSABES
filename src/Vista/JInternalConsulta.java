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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kalas
 */
public class JInternalConsulta extends javax.swing.JInternalFrame {
       String matricula; 
       DefaultTableModel modelo = new DefaultTableModel(); 
       List<Vconsultaalumnos> alumnos = new ArrayList<>();
       Calumno calumno = new Calumno();
       Object [] columnas= new Object[6];
       Object [] datos= new Object[6];
       DefaultComboBoxModel modelocarrera, modelostatus;
       
    /**
     * Creates new form JInternalConsulta
     */
    public JInternalConsulta() {
        initComponents();
        columnas [0]="Matricula";
        columnas [1]="Nombre";
        columnas [2]="Apellido";
        columnas [3]="Telefono";
        columnas [4]="Carrera";
        columnas [5]="Status";
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtmatricula)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
                            .addComponent(lblmatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnombre))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtapellido))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbcarrera)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbstatus)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatriculaKeyTyped
               
    }//GEN-LAST:event_txtmatriculaKeyTyped

    private void txtmatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmatriculaKeyReleased
       modelo.setRowCount(0);
        matricula= txtmatricula.getText();
           try {
               alumnos=calumno.consulta(Conexion.obtener(), matricula);
               for(int i=0;i<=alumnos.size()-1;i++)
               {
                   datos[0]=alumnos.get(i).getMatricula();
                   datos[1]=alumnos.get(i).getNombre();
                   datos[2]=alumnos.get(i).getApellidos();
                   datos[3]=alumnos.get(i).getTelefono();
                   datos[4]=alumnos.get(i).getCarrera();
                   datos[5]=alumnos.get(i).getStatus();
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
        int y= tblalumnos.getSelectedRow();
          int x=tblalumnos.getSelectedColumn();
          System.out.println(x);
          System.out.println(y);
          System.out.println(tblalumnos.getValueAt(y, 0));
          
          
    }//GEN-LAST:event_tblalumnosMouseClicked

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
    private javax.swing.JComboBox<String> cbbcarrera;
    private javax.swing.JComboBox<String> cbbstatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lblmatricula;
    private javax.swing.JTable tblalumnos;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtmatricula;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
