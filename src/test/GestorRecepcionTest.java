package test;

import Logica.GestorRecepcion;

public class GestorRecepcionTest {

    public static void main(String[] args) {

        GestorRecepcion sistema = new GestorRecepcion();

        System.out.println("=== Test 1: Registrar paciente ===");
        sistema.registrarPaciente("11111111", "Laura", "Test", 30);
        sistema.mostrarHistorial("11111111");  // Debería no mostrar nada (historial vacío)

        System.out.println("\n=== Test 2: Agendar turno común ===");
        sistema.agendarTurno("Laura", "Test", "11111111", "2025-06-21 10:00", 1);
        sistema.mostrarTurnosDelDia();  // Debería mostrar un turno común

        System.out.println("\n=== Test 3: Agregar urgencia y verificar prioridad ===");
        sistema.registrarUrgencia("Victoria","Sanchez","11111111", "2025-06-21 09:00", 5); // Prioridad alta

        System.out.println("\n→ Atendiendo primer paciente (debería ser la urgencia)...");
        sistema.atenderPaciente("Dolor de cabeza", "Requiere descanso", "2025-06-21");

        System.out.println("\n→ Atendiendo segundo paciente (turno normal)...");
        sistema.atenderPaciente("Control general", "Sin novedad", "2025-06-21");

        System.out.println("\n=== Ver historial de Laura Test ===");
        sistema.mostrarHistorial("11111111");  // Debería mostrar 2 consultas en orden de atención

        System.out.println("\n=== Test terminado ===");
    }
}
