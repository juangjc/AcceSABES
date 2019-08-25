/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Vacceso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kalas
 */
public class Cvacceso {
    
    Vacceso vacceso= new Vacceso();
    public Vacceso verificar(Connection conexion,String codigo){
         PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("SELECT matricula,nombre,apellido,fotografia FROM vacceso where status='Inscrito' and codigotarjeta='"+codigo+"'");
             ResultSet resultado = consulta.executeQuery();
             resultado.next();
             vacceso.setMatricula(resultado.getString("matricula"));
             vacceso.setNombre(resultado.getString("nombre"));
             vacceso.setApellido(resultado.getString("apellido"));
             vacceso.setFotografia(resultado.getBlob("fotografia"));
              
        
        } catch (SQLException ex) {
            Logger.getLogger(Cvacceso.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    
    
    return vacceso;
    }
    
}
