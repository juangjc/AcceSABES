/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author MUÑOZ
 */
public class Vprestamo_biblioteca {
    private int id_prestamo;
    private int id_libro;
    private String matricula;
    private String fecha_inicio;
    private String fecha_final;

    public Vprestamo_biblioteca(int id_prestamo, int id_libro, String matricula, String fecha_inicio, String fecha_final) {
        this.id_prestamo = id_prestamo;
        this.id_libro = id_libro;
        this.matricula = matricula;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
    }

    public Vprestamo_biblioteca() {
        
    }

    public Vprestamo_biblioteca(String matricula, int id_libro, String fecha_inicio, String fecha_final) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }
    
           
          
}