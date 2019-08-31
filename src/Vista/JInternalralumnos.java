/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Calumno;
import Controlador.Conexion;
import Modelo.Alumno;
import Modelo.Alumnotarjeta;
import Modelo.Carrera;
import Modelo.Status;
import static Vista.Ralumnos.modelo;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Kalas
 */
public class JInternalralumnos extends javax.swing.JInternalFrame {
  PanamaHitek_Arduino arduino =new  PanamaHitek_Arduino();
    String idtarjeta=null;
String dato;
 InternalFrameListener listener1= new InternalFrameListener () {
      @Override
      public void internalFrameOpened(InternalFrameEvent e) {
         
      }

      @Override
      public void internalFrameClosing(InternalFrameEvent e) {
            try {
              arduino.killArduinoConnection();
          } catch (ArduinoException ex) {
              Logger.getLogger(JInternalralumnos.class.getName()).log(Level.SEVERE, null, ex);
          }
      }

      @Override
      public void internalFrameClosed(InternalFrameEvent e) {
       
          try {
              arduino.killArduinoConnection();
          } catch (ArduinoException ex) {
              Logger.getLogger(JInternalralumnos.class.getName()).log(Level.SEVERE, null, ex);
          }
       
      }

      @Override
      public void internalFrameIconified(InternalFrameEvent e) {
          
      }

      @Override
      public void internalFrameDeiconified(InternalFrameEvent e) {
         
      }

      @Override
      public void internalFrameActivated(InternalFrameEvent e) {
          
      }

      @Override
      public void internalFrameDeactivated(InternalFrameEvent e) {
          
      }
  };

    private SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                
                if (arduino.isMessageAvailable()) {
                  
                    idtarjeta=arduino.printMessage();
                    System.out.println(idtarjeta);
                  txttarjeta.setBackground(Color.GREEN);
                 
                   txttarjeta.setText("Registrada");
                   arduino.killArduinoConnection();
                    
                   System.out.println("entro al label");
                }
            } catch (SerialPortException | ArduinoException ex) {
             txttarjeta.setBackground(Color.RED);
                 
                   txttarjeta.setText("Ocurrio un problema");
            }
        }

    };
    static DefaultComboBoxModel modelo,modelo2;
    String matricula,nombre,apellido, telefono,tarjeta;
    int idcarrera,idstatus,control;
    Blob fotografia=null;
byte[] imagen;
Alumno alumno;
Alumnotarjeta alumnotarjeta;
Calumno calumno=new Calumno();
    /**
     * Creates new form JInternalralumnos
     */
    public JInternalralumnos() {
        initComponents();
        addInternalFrameListener(listener1);
        
        try {
           
             arduino.arduinoRXTX("COM4", 9600, listener);
       
         //System.out.println(arduino.getInputBytesAvailable());
         
    } catch (Exception ex) {
       // Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
        
    }
        modelo = new DefaultComboBoxModel();
        modelo2 = new DefaultComboBoxModel();
        llena_combo_carrera();
        llena_combo_status();
        ImageIcon imagen = new ImageIcon("src/iconos/sn.png");
        Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
    }
    public void llena_combo_carrera() { // static para poder llamarlo desde el otro frame o JDialog
    Carrera carrera;
       
try {
    modelo.removeAllElements(); // eliminamos lo elementos
    Connection conexion = Conexion.obtener();
    PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM carrera" );
    ResultSet rs=consulta.executeQuery();
    while(rs.next())
    {       
        carrera= new Carrera(rs.getInt(1),rs.getString(2));
        modelo.addElement(carrera);
       
    }
     cbbcarrera.setModel(modelo); // seteamos el modelo y se cargan los datos
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
    
}   catch (ClassNotFoundException ex) {
        Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
    }}
    public void llena_combo_status() { // static para poder llamarlo desde el otro frame o JDialog
    Status status;
       
try {
    modelo2.removeAllElements(); // eliminamos lo elementos
    Connection conexion = Conexion.obtener();
    PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM status" );
    ResultSet rs=consulta.executeQuery();
    while(rs.next())
    {       
        status= new Status(rs.getInt(1),rs.getString(2));
        modelo2.addElement(status);
       
    }
     cbbstatus.setModel(modelo2); // seteamos el modelo y se cargan los datos
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
    
}   catch (ClassNotFoundException ex) {
        Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
    }}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbbstatus = new javax.swing.JComboBox<>();
        txtmatricula = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        webcam = new JPanelWebCam.JPanelWebCam();
        txtnombre = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        txttarjeta = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbcarrera = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        setOpaque(true);

        jLabel1.setText("Matricula:");

        cbbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbstatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbstatusItemStateChanged(evt);
            }
        });

        jButton1.setText("Tomar foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre: ");

        webcam.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        webcam.setToolTipText("Da click para activar camara");
        webcam.setFONDO(false);
        webcam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                webcamKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout webcamLayout = new javax.swing.GroupLayout(webcam);
        webcam.setLayout(webcamLayout);
        webcamLayout.setHorizontalGroup(
            webcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );
        webcamLayout.setVerticalGroup(
            webcamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellidos:");

        jLabel7.setText("Tarjeta:");

        txttarjeta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txttarjeta.setOpaque(true);

        jLabel4.setText("Telefono:");

        jLabel5.setText("Carrera");

        cbbcarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbcarrera.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbcarreraItemStateChanged(evt);
            }
        });

        jLabel6.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cbbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton2))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtapellidos)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(3, 3, 3)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(webcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(238, 238, 238))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(0, 95, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmatricula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(txttarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbstatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbstatusItemStateChanged
        Status st =(Status)this.cbbstatus.getSelectedItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            int id = st.getIdstatus();

        }
    }//GEN-LAST:event_cbbstatusItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        imagen =webcam.getBytes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void webcamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_webcamKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_webcamKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        matricula=txtmatricula.getText();
        nombre=txtnombre.getText();
        apellido=txtapellidos.getText();
        telefono= txttelefono.getText();
        Carrera cr =(Carrera)this.cbbcarrera.getSelectedItem();
        idcarrera = cr.getIdcarrera();
        Status st =(Status)this.cbbstatus.getSelectedItem();
        idstatus  = st.getIdstatus();
        tarjeta=idtarjeta;
        try {
            fotografia = new SerialBlob(imagen );
        } catch (SQLException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        alumno=new Alumno(matricula,nombre,apellido,telefono,idcarrera,idstatus,fotografia);
        alumnotarjeta= new Alumnotarjeta(matricula,tarjeta);
        try {
            calumno.guardar(Conexion.obtener(), alumno);
            try {
                Thread.sleep(2000);
                calumno.guardartarjeta(Conexion.obtener(), alumnotarjeta);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("hilo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbcarreraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbcarreraItemStateChanged
        Carrera cr =(Carrera)this.cbbcarrera.getSelectedItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            int id = cr.getIdcarrera();

        }
    }//GEN-LAST:event_cbbcarreraItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbcarrera;
    private javax.swing.JComboBox<String> cbbstatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtmatricula;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JLabel txttarjeta;
    private javax.swing.JTextField txttelefono;
    private JPanelWebCam.JPanelWebCam webcam;
    // End of variables declaration//GEN-END:variables
}
