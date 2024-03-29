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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Kalas
 */
public class JInternalralumnos extends javax.swing.JInternalFrame {

    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    String idtarjeta = null;
    String dato;
    static DefaultComboBoxModel modelo, modelo2;
    String matricula, nombre, apellido, telefono, tarjeta, email;
    int idcarrera, idstatus, control;
    Blob fotografia = null;
    byte[] imagen;
    long imagen2;
    Alumno alumno;
    Alumnotarjeta alumnotarjeta;
    Calumno calumno = new Calumno();
    //variables de la camara
    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;
    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (webSource.grab()) {
                        try {
                            //webSource.retrieve(frame);
                            webSource.read(frame);
                            Imgcodecs.imencode(".jpg", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = panelcamara.getGraphics();
                            // if (g.drawImage(buff, 0, 0, getWidth(), getHeight() -10 , 0, 0, buff.getWidth(), buff.getHeight(), null))
                            g.drawImage(buff, 0, 0, 550, 400, 0, 0, buff.getWidth(), buff.getHeight(), null);
                            if (runnable == false) {
                                System.out.println("Going to wait()");
                                this.wait();
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        }
                    }
                }
            }
        }
    }

    InternalFrameListener listener1 = new InternalFrameListener() {
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
                    idtarjeta = arduino.printMessage();
                    if(!calumno.verificartarjeta(Conexion.obtener(), idtarjeta)){
                    txttarjeta.setBackground(Color.orange);
                    txttarjeta.setText("Verificando.");
                    Thread.sleep(750);
                    txttarjeta.setText("Verificando..");
                    Thread.sleep(750);
                    txttarjeta.setText("Verificando...");
                    Thread.sleep(750);
                    txttarjeta.setText("Verificando....");
                    Thread.sleep(750);
                    txttarjeta.setBackground(Color.GREEN);
                    txttarjeta.setText("Registrada");}
                
                else{
                    txttarjeta.setBackground(Color.orange);
                    txttarjeta.setText("Verificando.");
                    Thread.sleep(750);
                    txttarjeta.setText("Verificando..");
                    Thread.sleep(750);
                    txttarjeta.setText("Verificando...");
                    Thread.sleep(750);
                    txttarjeta.setText("Verificando....");
                    Thread.sleep(750);
                txttarjeta.setBackground(Color.RED);
                txttarjeta.setText("Tarjeta previamente registrada");
                }}
            } catch (SerialPortException | ArduinoException ex) {
                txttarjeta.setBackground(Color.RED);
                txttarjeta.setText("Ocurrio un problema");
            } catch (InterruptedException ex) {
                Logger.getLogger(JInternalralumnos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JInternalralumnos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JInternalralumnos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    };

    /**
     * Creates new form JInternalralumnos
     */
    public JInternalralumnos() {
        initComponents();
        addInternalFrameListener(listener1);
        try {
            arduino.arduinoRXTX("COM4", 9600, listener);
        } catch (Exception ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);

        }
        modelo = new DefaultComboBoxModel();
        modelo2 = new DefaultComboBoxModel();
        llena_combo_carrera();
        llena_combo_status();
        ImageIcon imagen = new ImageIcon("src/iconos/sn.png");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
    }

    public void llena_combo_carrera() { // static para poder llamarlo desde el otro frame o JDialog
        Carrera carrera;

        try {
            modelo.removeAllElements(); // eliminamos lo elementos
            Connection conexion = Conexion.obtener();
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM carrera");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                carrera = new Carrera(rs.getInt(1), rs.getString(2));
                modelo.addElement(carrera);

            }
            cbbcarrera.setModel(modelo); // seteamos el modelo y se cargan los datos
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llena_combo_status() { // static para poder llamarlo desde el otro frame o JDialog
        Status status;

        try {
            modelo2.removeAllElements(); // eliminamos lo elementos
            Connection conexion = Conexion.obtener();
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM status");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                status = new Status(rs.getInt(1), rs.getString(2));
                modelo2.addElement(status);

            }
            cbbstatus.setModel(modelo2); // seteamos el modelo y se cargan los datos
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
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
        cbbstatus = new javax.swing.JComboBox<>();
        txtmatricula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
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
        panelcamara = new javax.swing.JPanel();
        btnactivar = new javax.swing.JButton();
        btntomar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Registro alumnos");

        jLabel1.setText("Matricula:");

        cbbstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbstatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbstatusItemStateChanged(evt);
            }
        });

        jLabel2.setText("Nombre: ");

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellidos:");

        jLabel7.setText("Tarjeta:");

        txtapellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidosActionPerformed(evt);
            }
        });
        txtapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidosKeyTyped(evt);
            }
        });

        txttarjeta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txttarjeta.setOpaque(true);

        jLabel4.setText("Telefono:");

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        jLabel5.setText("Carrera");

        cbbcarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbcarrera.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbcarreraItemStateChanged(evt);
            }
        });

        jLabel6.setText("Status");

        panelcamara.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelcamaraLayout = new javax.swing.GroupLayout(panelcamara);
        panelcamara.setLayout(panelcamaraLayout);
        panelcamaraLayout.setHorizontalGroup(
            panelcamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );
        panelcamaraLayout.setVerticalGroup(
            panelcamaraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        btnactivar.setText("Activar Camara");
        btnactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivarActionPerformed(evt);
            }
        });

        btntomar.setText("Tomar Fotografia");
        btntomar.setEnabled(false);
        btntomar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntomarActionPerformed(evt);
            }
        });

        jLabel8.setText("Email:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtmatricula))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtapellidos)
                            .addComponent(txtnombre)
                            .addComponent(txttelefono)
                            .addComponent(cbbcarrera, 0, 212, Short.MAX_VALUE)
                            .addComponent(txtemail)
                            .addComponent(cbbstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txttarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addGap(63, 63, 63)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
                        .addComponent(panelcamara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(494, 494, 494)
                        .addComponent(btnactivar)
                        .addGap(152, 152, 152)
                        .addComponent(btntomar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbcarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(49, 49, 49)
                                .addComponent(jButton2))
                            .addComponent(txttarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelcamara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntomar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbstatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbstatusItemStateChanged
        Status st = (Status) this.cbbstatus.getSelectedItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int id = st.getIdstatus();
        }
    }//GEN-LAST:event_cbbstatusItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (comprobarnombre()) {
            matricula = txtmatricula.getText();
            nombre = txtnombre.getText();
            apellido = txtapellidos.getText();
            telefono = txttelefono.getText();
            email = txtemail.getText();
            Carrera cr = (Carrera) this.cbbcarrera.getSelectedItem();
            idcarrera = cr.getIdcarrera();
            Status st = (Status) this.cbbstatus.getSelectedItem();
            idstatus = st.getIdstatus();
            tarjeta = idtarjeta;
            try {
                fotografia = new SerialBlob(imagen);

            } catch (SQLException ex) {
                Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
            }
            alumno = new Alumno(matricula, nombre, apellido, telefono, email, idcarrera, idstatus, fotografia);
            alumnotarjeta = new Alumnotarjeta(matricula, tarjeta);
            try {
                calumno.guardar(Conexion.obtener(), alumno);
                try {
                    Thread.sleep(500);
                    calumno.guardartarjeta(Conexion.obtener(), alumnotarjeta);
                    borrarcampos();
                    JOptionPane.showMessageDialog(this,
                            "Registro Insertado correctamente",
                            "Inserción de registro",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("hilo");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                        "Error al insertar registro, compruebe que la matricula a insertar no este ya registrada",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ralumnos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this,
                        "Error al insertar tarjeta",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al insertar registro, compruebe que todos los campos tengas datos a introducir",
                    "Campos vacios",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbcarreraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbcarreraItemStateChanged
        Carrera cr = (Carrera) this.cbbcarrera.getSelectedItem();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int id = cr.getIdcarrera();
        }
    }//GEN-LAST:event_cbbcarreraItemStateChanged

    private void btnactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactivarActionPerformed
        webSource = new VideoCapture(0);
        myThread = new DaemonThread();
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();        // TODO add your handling code here:
        btntomar.setEnabled(true);
        btnactivar.setEnabled(false);
    }//GEN-LAST:event_btnactivarActionPerformed

    public static byte[] long2bytearray(long l) {
        byte b[] = new byte[8];

        ByteBuffer buf = ByteBuffer.wrap(b);
        buf.putLong(l);
        return b;
    }

    private byte[] convertir2(Mat imagen) {
        MatOfByte mem2 = new MatOfByte();
        Imgcodecs.imencode(".bmp", imagen, mem2);
        byte[] byteArray = mem2.toArray();
        System.out.println(byteArray);
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }
    private void btntomarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntomarActionPerformed
        imagen = null;
        imagen = convertir2(frame);
        webSource.release();
        btnactivar.setEnabled(true);
        btntomar.setEnabled(false);
    }//GEN-LAST:event_btntomarActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c) == false) {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtapellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidosActionPerformed

    }//GEN-LAST:event_txtapellidosActionPerformed

    private void txtapellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidosKeyTyped
        int k = (int) evt.getKeyChar();
        System.out.println(k);

        if ((k >= 65 && k <= 90) || (k >= 97 && k <= 122) || (k == 32) || (k == 165)) {

        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        }
    }//GEN-LAST:event_txtapellidosKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
        int k = (int) evt.getKeyChar();
        System.out.println(k);

        if (k >= 48 && k <= 57) {

        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
        }

//if (k >= 97 && k <= 122 || k>=65 && k<=90){
//evt.setKeyChar((char)KeyEvent.VK_CLEAR);
////JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
//}
//if(k==241 || k==209){
//evt.setKeyChar((char)KeyEvent.VK_CLEAR);
////JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
//}

    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    public boolean comprobarnombre() {
        if (txtmatricula.getText().isEmpty() || txtnombre.getText().isEmpty() || txtapellidos.getText().isEmpty()
                || txttelefono.getText().isEmpty() || txtemail.getText().isEmpty() || txttarjeta.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void borrarcampos() {
        txtmatricula.setText("");
        txtnombre.setText("");
        txtapellidos.setText("");
        txttelefono.setText("");
        txtemail.setText("");
        txttarjeta.setText("");
        txttarjeta.setBackground(Color.LIGHT_GRAY);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactivar;
    private javax.swing.JButton btntomar;
    private javax.swing.JComboBox<String> cbbcarrera;
    private javax.swing.JComboBox<String> cbbstatus;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel panelcamara;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmatricula;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JLabel txttarjeta;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
