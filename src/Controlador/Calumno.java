/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Kalas
 */
public class Calumno {
     public void guardar(Connection conexion, Alumno alumno) throws SQLException{
     
         PreparedStatement consulta;
         try{
       consulta = conexion.prepareStatement("INSERT INTO alumno VALUES(?,?,?,?,?,?,?)");
          
            consulta.setString(1, alumno.getMatricula());
            consulta.setString(2,alumno.getNombre());
            consulta.setString(3, alumno.getApellido());
            consulta.setString(4,alumno.getTelefono());
            consulta.setInt(5, alumno.getIdcarrera());
            consulta.setInt(6, alumno.getIdstatus());
            consulta.setBlob(7, alumno.getFotografia());
            consulta.executeUpdate();}
         catch(SQLException ex){
         throw new SQLException(ex);
      }   
     
    
}
}
