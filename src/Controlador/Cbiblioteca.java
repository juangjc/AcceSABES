/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alta_libro;
import Modelo.Valumnos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
  public ArrayList<Alta_libro> consultalibro(Connection conexion, int id_libro) throws SQLException {
      ArrayList<Alta_libro> libro = new ArrayList();
      
      try {
            PreparedStatement consulta = conexion.prepareStatement("select * from libro where id_libro like '" + id_libro + "%'");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                libro.add(new Alta_libro(resultado.getInt("id_libro"),resultado.getString("nombre"), resultado.getString("clasificacion"), resultado.getString("autor"), resultado.getString("edicion"), resultado.getString("ISBN")));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return libro;
  }  
}
