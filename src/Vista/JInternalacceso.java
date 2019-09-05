/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Conexion;
import Controlador.Cvacceso;
import Controlador.Generales;
import Modelo.Accesom;
import Modelo.Vacceso;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Kalas
 */
public class JInternalacceso extends javax.swing.JInternalFrame {

    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    String codtarjeta, nombrecompleto, horaentrada;
    Vacceso vacceso = new Vacceso();
    Cvacceso cvacceso = new Cvacceso();
    Accesom accesom;
    ImageIcon imageicon;
    Blob bytesImagen;

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

                    codtarjeta = arduino.printMessage();
                    try {
                        lblnombre.setForeground(Color.black);
                        vacceso = cvacceso.verificar(Conexion.obtener(), codtarjeta);
                        if (vacceso.getApellido() != null) {
                            nombrecompleto = vacceso.getNombre() + " " + vacceso.getApellido();
                            System.out.println("nomre" + nombrecompleto);
                            bytesImagen = vacceso.getFotografia();
                            byte[] bytesLeidos = bytesImagen.getBytes(1, (int) bytesImagen.length());
                            BufferedImage img = null;
                            try {
                                img = ImageIO.read(new ByteArrayInputStream(bytesLeidos));
                            } catch (IOException ex) {
                                Logger.getLogger(JInternalacceso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            imageicon = new ImageIcon(img);
                            Icon icono = new ImageIcon(imageicon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
                            lblimagen.setIcon(imageicon);
                            lblpaso.setBackground(Color.GREEN);
                            lblnombre.setText(nombrecompleto);

                            //registro de entrdas descomentar las lineas de abajo 
                            horaentrada = Generales.getDateTime();
                            accesom = new Accesom(codtarjeta, horaentrada);
                            cvacceso.registraracceso(Conexion.obtener(), accesom);

                            inicial();

                        } else {
                            lblpaso.setBackground(Color.red);
                            lblnombre.setText("usuario no registrado o en situacion de baja ");
                            lblnombre.setForeground(Color.red);
                            lblimagen.setIcon(new ImageIcon(getClass().getResource("/iconos/sn.png")));
                            inicial();
                        }

//                  txttarjeta.setBackground(Color.GREEN);
//                 
//                   txttarjeta.setText("Registrada");
// arduino.killArduinoConnection();
                    } catch (SQLException ex) {
                        lblpaso.setBackground(Color.red);
                        Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        lblpaso.setBackground(Color.red);
                        Logger.getLogger(Acceso.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } catch (SerialPortException | ArduinoException ex) {
                lblpaso.setBackground(Color.red);
                // txttarjeta.setBackground(Color.RED);

                //txttarjeta.setText("Ocurrio un problema");
            }
        }

    };

    /**
     * Creates new form JInternalacceso
     */
    public JInternalacceso() {
        initComponents();

        addInternalFrameListener(listener1);
        try {
            arduino.arduinoRXTX("COM4", 9600, listener);

        } catch (Exception ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void inicial() {
        try {
            Thread.sleep(2300);
            lblnombre.setText("");
            lblpaso.setBackground(Color.gray);
            lblimagen.setIcon(new ImageIcon(getClass().getResource("/iconos/sn.png")));
        } catch (InterruptedException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hilo");
        }

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
        lblnombre = new javax.swing.JLabel();
        lblpaso = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Acceso");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Bienvenido:");

        lblnombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        lblpaso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblpaso.setOpaque(true);

        lblimagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sn.png"))); // NOI18N
        lblimagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblpaso, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(133, 133, 133)
                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addComponent(lblnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(lblpaso, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lblpaso;
    // End of variables declaration//GEN-END:variables

}
