/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Valumnos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kalas
 */
public class Cbiblioteca {
    Valumnos alumno= new Valumnos();
    
    public Valumnos consultaralumno (Connection conexion,String codtarjeta) throws SQLException
    {
        try {
            PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM `valumnosbiblioteca` WHERE STATUS='Inscrito' and tstatus=1 and codigotarjeta='"+codtarjeta+"'");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                alumno.setMatricula(resultado.getString("matricula"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setApellido(resultado.getString("apellido"));
               alumno.setEmail(resultado.getString("email"));
                alumno.setFotografia(resultado.getBlob("fotografia"));
                
            }
        } catch (SQLException ex) {
            System.out.println("hola");
            throw new SQLException(ex);
        }
        return alumno;
        
        
   
    }
    
}
