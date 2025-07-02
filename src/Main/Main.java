package Main;

import Logica.GestorRecepcion;
import java.util.Scanner;

import Entidades.Orden;
import Entidades.Paciente;

public class Main {
    public static void main(String[] args) {

        GestorRecepcion sistema = new GestorRecepcion();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Datos precargados
        sistema.registrarPaciente("12345678", "Ana", "Gómez", 34);
        sistema.registrarPaciente("87654321", "Carlos", "Pérez", 58);
        sistema.registrarUrgencia("Ana", "Gómez", "12345678", "2025-06-01 10:00", 1);
        sistema.registrarUrgencia("Carlos", "Pérez", "87654321", "2025-06-01 10:30", 1);
        sistema.registrarUrgencia("Ana", "Gómez", "12345678","2025-06-01 09:45", 5);

        do {
            System.out.println("\n========== MENÚ RECEPCIÓN HOSPITALARIA ==========");
            System.out.println("1. Registrar nuevo paciente");
            System.out.println("2. Agendar turno");
            System.out.println("3. Registrar urgencia");
            System.out.println("4. Atender siguiente paciente");
            System.out.println("5. Ver historial clínico de un paciente");
            System.out.println("6. Mostrar turnos del día");
            System.out.println("7. Mostrar todos los pacientes registrados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese DNI: ");
                    String dni = scanner.nextLine();
                    if (sistema.existePaciente(dni)) {
                        System.out.println("Ya existe un paciente con ese DNI.");
                        break;
                    }
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Ingrese edad: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Ingrese una edad válida.");
                        scanner.next();
                    }
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    sistema.registrarPaciente(dni, nombre, apellido, edad);
                    break;

                case 2:
                    System.out.print("DNI del paciente: ");
                    dni = scanner.nextLine();
                    if (!sistema.existePaciente(dni)) {
                        System.out.println("No existe un paciente con ese DNI.");
                        break;
                    }
                    Paciente pacienteOrden = sistema.getPacientePorDni(dni);
                    nombre = pacienteOrden.getNombre();
                    apellido = pacienteOrden.getApellido();
                    System.out.print("Fecha y hora del turno (YYYY-MM-DD HH:MM): ");
                    String fechaHora = scanner.nextLine();
                    sistema.registrarUrgencia(nombre, apellido, dni, fechaHora, 1);
                    System.out.println("Orden agendado correctamente.");
                    break;

                case 3:
                    System.out.print("DNI del paciente: ");
                    dni = scanner.nextLine();
                    if (!sistema.existePaciente(dni)) {
                        System.out.println("No existe un paciente con ese DNI.");
                        break;
                    }
                    Paciente pacienteUrg = sistema.getPacientePorDni(dni);
                    nombre = pacienteUrg.getNombre();
                    apellido = pacienteUrg.getApellido();
                    System.out.print("Fecha y hora de urgencia (YYYY-MM-DD HH:MM): ");
                    fechaHora = scanner.nextLine();
                    System.out.print("Prioridad (1 a 5): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Ingrese una prioridad válida entre 1 y 5.");
                        scanner.next();
                    }
                    int prioridad = scanner.nextInt();
                    scanner.nextLine();
                    sistema.registrarUrgencia(nombre, apellido, dni, fechaHora, prioridad);
                    break;

                case 4:
                    Orden siguiente = sistema.verProximaOrden();
                    if (siguiente == null) {
                        System.out.println("No hay pacientes en espera.");
                        break;
                    }
                    Paciente p = sistema.getPacientePorDni(siguiente.getDniPaciente());
                    if (p == null) {
                        System.out.println("Paciente no encontrado.");
                        break;
                    }
                    System.out.println("Atendiendo a: " + p.getNombre() + " " + p.getApellido());

                    System.out.print("Motivo de consulta: ");
                    String motivo = scanner.nextLine();
                    System.out.print("Diagnóstico: ");
                    String diagnostico = scanner.nextLine();
                    System.out.print("Fecha de atención (YYYY-MM-DD): ");
                    String fecha = scanner.nextLine();

                    sistema.atenderPaciente(motivo, diagnostico, fecha);
                    break;

                case 5:
                    System.out.print("DNI del paciente: ");
                    dni = scanner.nextLine();
                    if (!sistema.existePaciente(dni)) {
                        System.out.println("Paciente no encontrado.");
                        break;
                    }
                    System.out.println("--- Historial clínico ---");
                    sistema.mostrarHistorial(dni);
                    break;

                case 6:
                    System.out.println("--- Turnos del día ---");
                    sistema.mostrarTurnosDelDia();
                    break;

                case 7:
                    System.out.println("Lista de pacientes:");
                    sistema.mostrarPacientes();
                    break;

                case 0:
                    System.out.println("Cerrando sistema...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}



