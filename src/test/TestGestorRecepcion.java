//package test;
//
//import Logica.GestorRecepcion;
//
//public class TestGestorRecepcion {
//
//        public static void main(String[] args) {
//
//            GestorRecepcion sistema = new GestorRecepcion();
//
//            System.out.println("=== Test 1: Registrar paciente ===");
//            sistema.registrarPaciente("11111111", "Laura", "Test", 30);
//            sistema.mostrarHistorial("11111111");  // Debería no mostrar nada (historial vacío)
//
//            System.out.println("\n=== Test 2: Agendar turno común ===");
//            sistema.agendarTurno("11111111", "2025-06-21 10:00");
//            sistema.mostrarTurnosDelDia();  // Debería mostrar un turno común
//
//            System.out.println("\n=== Test 3: Agregar urgencia y verificar prioridad ===");
//            sistema.registrarUrgencia("11111111", "2025-06-21 09:00");
//
//            System.out.println("\n→ Atendiendo primer paciente...");
//            sistema.atenderPaciente("Dolor de cabeza", "Requiere descanso", "2025-06-21");
//
//            System.out.println("\n→ Atendiendo segundo paciente...");
//            sistema.atenderPaciente("Control general", "Sin novedad", "2025-06-21");
//
//            System.out.println("\n=== Ver historial de Laura Test ===");
//            sistema.mostrarHistorial("11111111");  // Debería mostrar 2 consultas en orden inverso
//
//            System.out.println("\n=== Test terminado===");
//}
//    }
