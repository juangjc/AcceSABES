/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Alumnotarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kalas
 */
public class Calumno {
     public void guardar(Connection conexion, Alumno alumno) throws SQLException{
        PreparedStatement consulta;
         try {
             consulta = conexion.prepareStatement("INSERT INTO alumno VALUES(?,?,?,?,?,?,?)");
             consulta.setString(1, alumno.getMatricula());
             consulta.setString(2, alumno.getNombre());
             consulta.setString(3, alumno.getApellido());
             consulta.setString(4, alumno.getTelefono());
             consulta.setInt(5, alumno.getIdcarrera());
             consulta.setInt(6, alumno.getIdstatus());
             consulta.setBlob(7, alumno.getFotografia());
             consulta.executeUpdate();
         } catch (SQLException ex) {
             throw new SQLException(ex);
         }
     
    
}
     
     public void guardartarjeta(Connection conexion,Alumnotarjeta alumnotarjeta) throws SQLException{
         PreparedStatement consulta;
         try {
             consulta = conexion.prepareStatement("INSERT INTO alumno_tarjeta VALUES(?,?)");
             consulta.setString(1, alumnotarjeta.getMatricula());
             consulta.setString(2, alumnotarjeta.getCodigotarjeta());
             consulta.executeUpdate();
         } catch (SQLException ex) {
             throw new SQLException(ex);
         }  
     
     
     
     }
     
     public ArrayList<Alumno> consulta(Connection conexion,String matricula) throws SQLException
     {
         ArrayList<Alumno> alumno = new ArrayList();         
           try{
         PreparedStatement consulta = conexion.prepareStatement("select * from alumno where matricula like '"+matricula+"%'");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            alumno.add(new Alumno(resultado.getString("matricula"), resultado.getString("nombre"),resultado.getString("apellido"),resultado.getString("telefono"),resultado.getInt("idcarrera"),
            resultado.getInt("idstatus"),resultado.getBlob("fotografia")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
     
         return alumno;
     }
}
