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
    
    public void Guardarinfo (Connection conexion, Vprestamo_biblioteca id_prestamo) throws SQLException{
        PreparedStatement consulta;
        try {
            consulta = conexion.prepareStatement("INSERT INTO prestamo_libro_alumno (id_libro, matricula, fecha_prestamo, fecha_entrega,id_status) VALUES(?,?,?,?,1)");
            consulta.setInt(1, id_prestamo.getId_libro());
            consulta.setString(2,id_prestamo.getMatricula());
             consulta.setString(3, id_prestamo.getFecha_inicio());
            consulta.setString(4, id_prestamo.getFecha_final());
           
            consulta.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("hola");
            throw new SQLException(ex);
        }
    }
}
