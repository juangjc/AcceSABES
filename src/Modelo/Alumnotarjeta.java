/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Kalas
 */
public class Alumnotarjeta {
    private String matricula;
    private String codigotarjeta;

    public Alumnotarjeta(String matricula, String codigotarjeta) {
        this.matricula = matricula;
        this.codigotarjeta = codigotarjeta;
    }
    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCodigotarjeta() {
        return codigotarjeta;
    }

    public void setCodigotarjeta(String codigotarjeta) {
        this.codigotarjeta = codigotarjeta;
    }
    
    
}
