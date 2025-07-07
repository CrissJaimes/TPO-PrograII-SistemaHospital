package Main;

import Logica.GestorRecepcion;
import java.time.LocalDate;
import java.util.Scanner;
import Entidades.Orden;
import Entidades.Paciente;

public class Main {
    public static void main(String[] args) {

        GestorRecepcion sistema = new GestorRecepcion();
        Scanner scanner = new Scanner(System.in);
        int opcion;


        sistema.registrarPaciente("12345678", "Ana", "Gómez", LocalDate.ofEpochDay(1998-02-1));
        sistema.registrarPaciente("87654321", "Carlos", "Pérez", LocalDate.ofEpochDay(1991-02-21));
        sistema.registrarPaciente("35428821", "Sofía", "Ramírez", LocalDate.of(1994, 3, 15));
        sistema.registrarPaciente("55432210", "Mateo", "Fernández", LocalDate.of(1988, 11, 5));
        sistema.registrarPaciente("20620198", "Lucía", "Gómez", LocalDate.of(2001, 7, 22));
        sistema.registrarPaciente("33445566", "Tomás", "Riquelme", LocalDate.of(1992, 3, 18));
        sistema.registrarPaciente("44556677", "Martina", "Fernández", LocalDate.of(2015, 11, 5));

        sistema.registrarUrgencia("35428821", "clinica", "dolor abdominal agudo", "gastritis",2);
        sistema.registrarUrgencia("55432210", "clinica", "fiebre y vomitos", "virus", 4);
        sistema.registrarUrgencia("20620198", "clinica", "caida","fractura de rotula sin desplazamiento", 3);
        sistema.registrarUrgencia("12345678", "clinica", "caída de bicicleta", "raspado", 1);
        sistema.registrarUrgencia("87654321", "clinica", "dolor en el pecho", "posible angina",1);
        sistema.registrarUrgencia("33445566", "traumatologia", "accidente en bicicleta", "luxación de hombro izquierdo", 2);
        sistema.registrarUrgencia("44556677", "pediatria", "fiebre y vómitos", "gastroenteritis viral", 4);

        do {
            System.out.println("\n========== MENÚ RECEPCIÓN GUARDIA HOSPITALARIA ==========");
            System.out.println("1. Registrar nuevo paciente");
            System.out.println("2. Registrar urgencia");
            System.out.println("3. Atender siguiente paciente");
            System.out.println("4. Ver historial clínico de un paciente");
            System.out.println("5. Mostrar pacientes del día");
            System.out.println("6. Mostrar todos los pacientes registrados");
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
                    String dni = scanner.nextLine().trim();;
                    if (sistema.existePaciente(dni)) {
                        System.out.println("Ya existe un paciente con ese DNI.");
                        break;
                    }
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Ingrese Fecha de Nacimiento (formato yyyy-MM-dd): ");
                    String input = scanner.nextLine();
                    LocalDate fechaNacimiento = LocalDate.parse(input); // SOLO si cumple bien el formato
                    sistema.registrarPaciente(dni, nombre, apellido, fechaNacimiento);
                    break;

                case 2:
                    System.out.print("DNI del paciente: ");
                    dni = scanner.nextLine();
                    if (!sistema.existePaciente(dni)) {
                        System.out.println("No existe un paciente con ese DNI.");
                        break;
                    }
                    System.out.print("Ingrese especialidad (clinica, pediatria, traumatologia): ");
                    String especialidad = scanner.nextLine();
                    System.out.print("Motivo: ");
                    String motivo = scanner.nextLine();
                    System.out.print("Diagnostico: ");
                    String diagnostico = scanner.nextLine();
                    System.out.print("Prioridad (1 a 5): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Ingrese una prioridad válida entre 1 y 5.");
                        scanner.next();
                    }
                    int prioridad = scanner.nextInt();
                    scanner.nextLine();
                    sistema.registrarUrgencia(dni, especialidad, motivo, diagnostico, prioridad);
                    break;

                case 3:
                    System.out.print("Ingrese especialidad (clinica, pediatria, traumatologia): ");
                    especialidad = scanner.nextLine();
                    Orden siguiente = sistema.verProximaOrden(especialidad);
                    if (siguiente == null) {
                        System.out.println("No hay pacientes en espera.");
                        break;
                    }
                    Paciente p = sistema.getPacientePorDni(siguiente.getDniPaciente());
                    if (p == null) {
                        System.out.println("Paciente no encontrado.");
                        break;
                    }

                    sistema.atenderPaciente(especialidad);
                    break;

                case 4:
                    System.out.print("DNI del paciente: ");
                    dni = scanner.nextLine();
                    if (!sistema.existePaciente(dni)) {
                        System.out.println("Paciente no encontrado.");
                        break;
                    }
                    System.out.println("--- Historial clínico ---");
                    sistema.mostrarHistorial(dni);
                    break;

                case 5:
                    System.out.println("--- Consulta de pacientes ---");
                    System.out.print("Ingrese una fecha (yyyy-MM-dd) o escriba 'todos' para ver todos los pacientes: ");
                    String entrada = scanner.nextLine().trim();

                    if (entrada.equalsIgnoreCase("todos") || entrada.isEmpty()) {
                        sistema.mostrarTurnosDelDia();
                    } else {
                        sistema.mostrarTurnosPorFecha(entrada);
                        }
                    break;

                case 6:
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


