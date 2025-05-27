/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

public class Postulante implements Serializable {
    private String cedula;
    private String nombre;
    private Carrera carreraPostulada;
    private double puntajeExamen;
    private int puntajeAdicional;
    private String tipoExamen;
    private boolean aprobado;

    public Postulante(String cedula, String nombre, Carrera carreraPostulada,
                      double puntajeExamen, int puntajeAdicional, String tipoExamen, boolean aprobado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.carreraPostulada = carreraPostulada;
        this.puntajeExamen = puntajeExamen;
        this.puntajeAdicional = puntajeAdicional;
        this.tipoExamen = tipoExamen;
        this.aprobado = aprobado;
    }

    public String getCedula() { 
        return cedula; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public Carrera getCarreraPostulada() { 
        return carreraPostulada; 
    }
    
    public double getPuntajeTotal() { 
        return puntajeExamen + puntajeAdicional; 
    
    }
    
    public boolean isAprobado() { 
        return aprobado; 
    }
    
    public void setAprobado(boolean aprobado) { 
        this.aprobado = aprobado; 
    }
    
    public String getTipoExamen() { 
        return tipoExamen; 
    }

    @Override
    public String toString() {
        return nombre + " (" + cedula + ") - " + carreraPostulada.getNombre();
    }
}
