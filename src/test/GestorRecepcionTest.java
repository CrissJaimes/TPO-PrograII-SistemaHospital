package test;

import Logica.GestorRecepcion;
import Entidades.Orden;
import Entidades.Paciente;

import java.time.LocalDate;

public class GestorRecepcionTest {

    public static void main(String[] args) {
        testRegistrarPacienteValido();
        testRegistrarPacienteInvalido();
        testRegistrarUrgenciaSinPaciente();
        testRegistrarUrgenciaValida();
        testAtenderPaciente();
    }

    private static void testRegistrarPacienteValido() {
        System.out.println("=== Test: registrarPaciente válido ===");
        GestorRecepcion gestor = new GestorRecepcion();
        gestor.registrarPaciente("12345678", "Ana", "Gómez", LocalDate.of(1995, 6, 15));

        if (gestor.existePaciente("12345678")) {
            System.out.println("Paciente registrado correctamente.");
        } else {
            System.out.println("Falló el registro del paciente.");
        }
    }

    private static void testRegistrarPacienteInvalido() {
        System.out.println("=== Test: registrarPaciente con DNI inválido ===");
        GestorRecepcion gestor = new GestorRecepcion();
        gestor.registrarPaciente("abc123", "Luis", "Martínez", LocalDate.of(1990, 1, 1));

        if (!gestor.existePaciente("abc123")) {
            System.out.println("No se registró el paciente con DNI inválido.");
        } else {
            System.out.println("El paciente con DNI inválido fue registrado.");
        }
    }

    private static void testRegistrarUrgenciaSinPaciente() {
        System.out.println("=== Test: registrarUrgencia sin registrar paciente ===");
        GestorRecepcion gestor = new GestorRecepcion();
        gestor.registrarUrgencia("99999999", "clinica", "dolor", "gripe", 2);

        Orden orden = gestor.verProximaOrden("clinica");
        if (orden == null) {
            System.out.println("No se registró urgencia para paciente inexistente.");
        } else {
            System.out.println("Se registró urgencia sin paciente.");
        }
    }

    private static void testRegistrarUrgenciaValida() {
        System.out.println("=== Test: registrarUrgencia válida ===");
        GestorRecepcion gestor = new GestorRecepcion();
        gestor.registrarPaciente("23456789", "Carlos", "Pérez", LocalDate.of(1980, 5, 20));
        gestor.registrarUrgencia("23456789", "pediatria", "fiebre", "infección", 3);

        Orden orden = gestor.verProximaOrden("pediatria");
        if (orden != null && orden.getDniPaciente().equals("23456789")) {
            System.out.println("Urgencia registrada correctamente.");
        } else {
            System.out.println("Falló el registro de urgencia.");
        }
    }

    private static void testAtenderPaciente() {
        System.out.println("=== Test: atenderPaciente ===");
        GestorRecepcion gestor = new GestorRecepcion();
        gestor.registrarPaciente("34567890", "Lucía", "Ramos", LocalDate.of(2000, 12, 10));
        gestor.registrarUrgencia("34567890", "traumatologia", "fractura", "tibia", 1);

        gestor.atenderPaciente("traumatologia");

        Orden orden = gestor.verProximaOrden("traumatologia");
        if (orden == null) {
            System.out.println("El paciente fue atendido correctamente.");
        } else {
            System.out.println("El paciente no fue atendido.");
        }
    }
}
