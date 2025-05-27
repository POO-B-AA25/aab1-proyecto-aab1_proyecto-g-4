/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrera implements Serializable {
    private String nombre;
    private int cupos;
    private String tipoAdmision;
    private double puntajeMinimo;
    private ArrayList<Postulante> postulantes;

    public Carrera(String nombre, int cupos, String tipoAdmision, double puntajeMinimo) {
        this.nombre = nombre;
        this.cupos = cupos;
        this.tipoAdmision = tipoAdmision;
        this.puntajeMinimo = puntajeMinimo;
        this.postulantes = new ArrayList<>();
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public int getCupos() { 
        return cupos; 
    }
    
    public ArrayList<Postulante> getPostulantes() { 
        return postulantes; 
    }

    public void agregarPostulante(Postulante p) {
        postulantes.add(p);
    }

    public double getPuntajeMinimo() {
        return puntajeMinimo;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipoAdmision + ")";
    }
}