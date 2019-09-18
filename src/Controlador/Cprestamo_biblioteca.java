/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Vprestamo_biblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MUÑOZ
 */
public class Cprestamo_biblioteca {
    Vprestamo_biblioteca biblioteca = new Vprestamo_biblioteca();
    
    public void Guardarinfo (Connection conexion, Vprestamo_biblioteca id_prestamo) throws SQLException{
        PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("INSERT INTO prestamo_libro_alumno VALUES(?,?,?,?,?)");
            consulta.setInt(1, biblioteca.getId_prestamo());
            consulta.setInt(2, biblioteca.getId_libro());
            consulta.setString(3,biblioteca.getMatricula());
             consulta.setString(4, biblioteca.getFecha_inicio());
            consulta.setString(5, biblioteca.getFecha_final());
           
            consulta.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("hola");
            throw new SQLException(ex);
        }
    }
}