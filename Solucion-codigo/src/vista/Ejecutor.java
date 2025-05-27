package vista;

import controlador.SistemaAdmision;
import modelo.Carrera;
import modelo.Postulante;

import java.util.Scanner;

public class Ejecutor {
    public static void main(String[] args) {
        SistemaAdmision sistema = new SistemaAdmision();
        Scanner sc = new Scanner(System.in);
        sistema.cargarDatos();

        int opcion;
        do {
            System.out.println("\n--- Menú de Admisiones UTPL ---");
            System.out.println("1. Registrar carrera");
            System.out.println("2. Registrar postulante");
            System.out.println("3. Procesar admisiones");
            System.out.println("4. Mostrar estadísticas");
            System.out.println("5. Guardar y salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre de la carrera: ");
                    String nombre = sc.nextLine();
                    System.out.print("Cupos disponibles: ");
                    int cupos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de admisión (examen/diagnostico): ");
                    String tipo = sc.nextLine();
                    double minimo = 0;
                    if (tipo.equalsIgnoreCase("examen")) {
                        System.out.print("Puntaje mínimo: ");
                        minimo = sc.nextDouble();
                        sc.nextLine();
                    }
                    Carrera c = new Carrera(nombre, cupos, tipo, minimo);
                    sistema.getListaCarreras().add(c);
                    System.out.println("Carrera registrada.");
                }

                case 2 -> {
                    if (sistema.getListaCarreras().isEmpty()) {
                        System.out.println("Debe registrar al menos una carrera antes.");
                        break;
                    }
                    System.out.print("Cédula: ");
                    String cedula = sc.nextLine();
                    System.out.print("Nombre del postulante: ");
                    String nombre = sc.nextLine();

                    System.out.println("Seleccione carrera:");
                    for (int i = 0; i < sistema.getListaCarreras().size(); i++) {
                        System.out.println((i + 1) + ". " + sistema.getListaCarreras().get(i).getNombre());
                    }
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    Carrera carrera = sistema.getListaCarreras().get(idx);

                    System.out.print("Puntaje de examen: ");
                    double puntajeExamen = sc.nextDouble();
                    System.out.print("Puntaje adicional: ");
                    int adicional = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo de examen (examen/diagnostico): ");
                    String tipoExamen = sc.nextLine();

                    Postulante postulante = new Postulante(cedula, nombre, carrera, puntajeExamen, adicional, tipoExamen, false);
                    sistema.registrarPostulante(postulante);
                    System.out.println("Postulante registrado.");
                }

                case 3 -> {
                    sistema.procesarAdmisiones();
                    System.out.println("Admisiones procesadas.");
                }

                case 4 -> sistema.generarEstadisticas();

                case 5 -> {
                    sistema.guardarDatos();
                    System.out.println("Datos guardados. Saliendo...");
                }

                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }
}
