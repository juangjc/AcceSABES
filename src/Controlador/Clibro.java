/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alta_libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author MUÑOZ
 */
public class Clibro {
    
    public void guardar(Connection conexion, Alta_libro libro) throws SQLException {
        PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("INSERT INTO libro VALUES(?,?,?,?,?,?)");
            consulta.setInt(1, libro.getId_libro());
            consulta.setString(2, libro.getNombre_libro());
            consulta.setString(3, libro.getClasificacion());
             consulta.setString(4, libro.getClasificacion());
            consulta.setString(5, libro.getEdicion());
            consulta.setString(6, libro.getIsbn());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }
    
    
    public void consulta_libro(Connection conexion, Alta_libro busqueda) throws SQLException {
        PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("SELECT*FROM libro WHERE id_libro = '"+busqueda+"';");
            consulta.setInt(1, busqueda.getId_libro());
            consulta.setString(2, busqueda.getNombre_libro());
            consulta.setString(3, busqueda.getClasificacion());
             consulta.setString(4, busqueda.getClasificacion());
            consulta.setString(5, busqueda.getEdicion());
            consulta.setString(6, busqueda.getIsbn());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

   
   
    
}
