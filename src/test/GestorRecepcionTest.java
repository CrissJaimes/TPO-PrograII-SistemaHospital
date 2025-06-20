package test;

import Logica.GestorRecepcion;

public class GestorRecepcionTest {

    public static void main(String[] args) {

        GestorRecepcion sistema = new GestorRecepcion();

                System.out.println("== Caso 1: Registrar paciente con nuevo DNI ==");
                String nombre1 = "Ana";
                String apellido1 = "García";
                sistema.registrarPaciente("12345678", nombre1, apellido1, 32);

                System.out.println("\n== Caso 2: Agendar turno común y verificar en la cola ==");
                sistema.registrarUrgencia("Cristian", "Jaimes", "12345678", "2025-06-20 10:00", 1);

                System.out.println("\n== Caso 3: Ingresar paciente por urgencia y verificar prioridad ==");
                String nombre2 = "Carlos";
                String apellido2 = "López";
                sistema.registrarPaciente("23456789", nombre2, apellido2, 45);
                sistema.registrarUrgencia("León", "Saavedra","23456789", "2025-06-20 10:05", 5); // Emergencia vital

                String nombre3 = "Beatriz";
                String apellido3 = "Díaz";
                sistema.registrarPaciente("34567890", nombre3 , apellido3, 28);
                sistema.registrarUrgencia("Cristina", "Gloria","34567890", "2025-06-20 10:10", 3); // Urgencia

                System.out.println("\n== Caso 4: Atender pacientes y verificar orden correcto ==");
                sistema.atenderPaciente("Dolor abdominal", "Apendicitis", "2025-06-20");
                sistema.atenderPaciente("Fiebre alta", "Gripe", "2025-06-20");
                sistema.atenderPaciente("Chequeo", "Sin novedades", "2025-06-20");

                System.out.println("\n== Caso 5: Consultar historial clínico de paciente después de varias atenciones ==");
                sistema.mostrarHistorial("23456789"); // Carlos
                sistema.mostrarHistorial("12345678"); // Ana
            }
        }
