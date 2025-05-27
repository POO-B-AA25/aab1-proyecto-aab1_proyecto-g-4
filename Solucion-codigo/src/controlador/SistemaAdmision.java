/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Carrera;
import modelo.Postulante;

import java.io.*;
import java.util.*;

public class SistemaAdmision {
    private ArrayList<Carrera> listaCarreras;
    private ArrayList<Postulante> listaPostulantes;

    public SistemaAdmision() {
        listaCarreras = new ArrayList<>();
        listaPostulantes = new ArrayList<>();
    }

    public void registrarPostulante(Postulante postulante) {
        listaPostulantes.add(postulante);
        postulante.getCarreraPostulada().agregarPostulante(postulante);
    }

    public void procesarAdmisiones() {
        for (Carrera carrera : listaCarreras) {
            ArrayList<Postulante> postulantesCarrera = carrera.getPostulantes();
            if (postulantesCarrera.isEmpty()) continue;

            if ("examen".equalsIgnoreCase(carrera.toString())) {
                postulantesCarrera.removeIf(p -> p.getPuntajeTotal() < carrera.getPuntajeMinimo());
            }

            postulantesCarrera.sort((a, b) -> Double.compare(b.getPuntajeTotal(), a.getPuntajeTotal()));

            int cupos = carrera.getCupos();
            for (int i = 0; i < postulantesCarrera.size(); i++) {
                Postulante p = postulantesCarrera.get(i);
                if (i < cupos) {
                    p.setAprobado(true);
                } else if ("diagnostico".equalsIgnoreCase(p.getTipoExamen()) && p.getPuntajeTotal() >= 30.0) {
                    p.setAprobado(true); 
                } else {
                    p.setAprobado(false);
                }
            }
        }
    }

    public void generarEstadisticas() {
        for (Carrera carrera : listaCarreras) {
            int inscritos = carrera.getPostulantes().size();
            int cupos = carrera.getCupos();
            long aprobados = carrera.getPostulantes().stream().filter(Postulante::isAprobado).count();

            System.out.println("Carrera: " + carrera.getNombre());
            System.out.println(" - Inscritos: " + inscritos);
            System.out.println(" - Cupos: " + cupos);
            System.out.println(" - Aprobados: " + aprobados);

            if (inscritos < cupos / 2.0) {
                System.out.println("   * ALERTA: Menos del 50% de ocupación");
            }

            if (inscritos > cupos) {
                System.out.println("   * Se rechazaron postulantes por falta de cupos");
            }
        }
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datos.dat"))) {
            oos.writeObject(listaCarreras);
            oos.writeObject(listaPostulantes);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datos.dat"))) {
            listaCarreras = (ArrayList<Carrera>) ois.readObject();
            listaPostulantes = (ArrayList<Postulante>) ois.readObject();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public ArrayList<Carrera> getListaCarreras() { return listaCarreras; }
    public ArrayList<Postulante> getListaPostulantes() { return listaPostulantes; }
}
