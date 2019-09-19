/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import java.awt.Image;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.opencv.core.Core;

/**
 *
 * @author Kalas
 */
public class FramePrueba extends javax.swing.JFrame {
JInternalralumnos ralumno;
JInternalacceso acceso;
Jinternalalta_libro accebiblioteca;
prestamo_libro prestamo;
JInternalaccesocc acessocc;
JInternalConsulta consulta;
String path = "/fondos/SABES-logo.jpg";  
URL url = this.getClass().getResource(path);  
ImageIcon icon = new ImageIcon(url);  


    /**
     * Creates new form FramePrueba
     */
    public FramePrueba() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(455, 637, Image.SCALE_DEFAULT));
                            lblimagen.setIcon(icono);

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dp = new javax.swing.JDesktopPane();
        lblimagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dp.setLayer(lblimagen, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dpLayout = new javax.swing.GroupLayout(dp);
        dp.setLayout(dpLayout);
        dpLayout.setHorizontalGroup(
            dpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpLayout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(489, Short.MAX_VALUE))
        );
        dpLayout.setVerticalGroup(
            dpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dpLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        getContentPane().add(dp, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1370, 710));

        jMenu1.setText("Accesos");

        jMenuItem1.setText("Alta alumnos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Acceso");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Consulta alumnos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem6.setText("Acceso CC");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Biblioteca");

        jMenuItem4.setText("Alta de libros");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Prestamos de libros");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       
       // cerrarventanas();
      
        ralumno = new JInternalralumnos();
 
        // Thread.sleep(2000);
        dp.removeAll();
        dp.add(ralumno, BorderLayout.CENTER);
        dp.revalidate();
        dp.repaint();
        try {
            ralumno.setMaximum(true);
            
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FramePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        ralumno.show();
   
       
            
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    //  cerrarventanas();
      
        acceso = new JInternalacceso();
        dp.removeAll();
        dp.add(acceso, BorderLayout.CENTER);
        dp.revalidate();
        dp.repaint();
         
        try {
            acceso.setMaximum(true);
            
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FramePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        acceso.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
      
        consulta = new JInternalConsulta();
        dp.removeAll();
        dp.add(consulta, BorderLayout.CENTER);
        dp.revalidate();
        dp.repaint();
        try {
            consulta.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FramePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        consulta.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         accebiblioteca = new Jinternalalta_libro();
        dp.removeAll();
        dp.add(accebiblioteca, BorderLayout.CENTER);
        dp.revalidate();
        dp.repaint();
        try {
            accebiblioteca.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FramePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        accebiblioteca.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
   
         prestamo = new prestamo_libro();
        dp.removeAll();
        dp.add(prestamo, BorderLayout.CENTER);
        dp.revalidate();
        dp.repaint();
        try {
            prestamo.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FramePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        prestamo.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
   ;
    acessocc = new JInternalaccesocc();        // TODO add your handling code here:
    dp.removeAll();
        dp.add(acessocc, BorderLayout.CENTER);
        dp.revalidate();
        dp.repaint();
         try {
            acessocc.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FramePrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        acessocc.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    public void cerrarventanas(){
       
            if(ralumno.isDisplayable()){
                System.out.println(ralumno.isDisplayable()+"hhhhhhh");
            ralumno.dispose();}
             if(acceso.isDisplayable()){   
                 System.out.println(acceso.isDisplayable()+"acceso");
              acceso.dispose();   }
            
//             if(ralumno.is){             
//             ralumno.dispose(); 
//                 System.out.println("se cerro alumno");
         //   } 
//             if(acceso.isDisplayable()){             
//             acceso.dispose();   
//                 System.out.println("Se cerro acceso");
//            }
//              if(acessocc.isDisplayable()){             
//             acessocc.dispose();    
//                  System.out.println("Se cerro accesocc");
//            }
//              if(prestamo.isDisplayable()){             
//             prestamo.dispose(); 
//                  System.out.println("Se cerro prestamo");
//            }
             
       
    }
    private static void loadLibraries() {

    try {
        InputStream in = null;
        File fileOut = null;
        String osName = System.getProperty("os.name");
        String opencvpath = System.getProperty("user.dir");
        if(osName.startsWith("Windows")) {
            int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
            if(bitness == 32) {
                opencvpath=opencvpath+"\\opencv\\x86\\";
            }
            else if (bitness == 64) { 
                opencvpath=opencvpath+"\\opencv\\x64\\";
            } else { 
                opencvpath=opencvpath+"\\opencv\\x86\\"; 
            }           
        } 
        else if(osName.equals("Mac OS X")){
            opencvpath = opencvpath+"Your path to .dylib";
        }
        System.out.println(opencvpath);
        System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
    } catch (Exception e) {
        throw new RuntimeException("Failed to load opencv native library", e);
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        loadLibraries();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dp;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JLabel lblimagen;
    // End of variables declaration//GEN-END:variables
}
