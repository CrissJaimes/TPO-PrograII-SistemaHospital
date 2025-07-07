package test;

import Entidades.Orden;
import TDA.ListaEnlazada;

public class TurnoTester {
    private ListaEnlazada<Orden> turnosDelDia;

    public TurnoTester(ListaEnlazada<Orden> turnos) {
        this.turnosDelDia = turnos;
    }

    public void mostrarTurnosDelDia() {
        if (turnosDelDia.estaVacia()) {
            System.out.println("No hay pacientes registrados para hoy.");
            return;
        }

        System.out.println("=== Pacientes del Día ===");

        for (Orden o : turnosDelDia) {
            System.out.println("--- Paciente ---");
            System.out.printf("DNI:           %s%n", o.getDniPaciente());
            System.out.printf("Prioridad:     %d%n", o.getPrioridad());
            System.out.printf("Fecha y Hora:  %s%n", o.getFechaHora());
            System.out.println("--------------");
        }
    }

    public void mostrarTurnosPorFecha(String fechaBuscada) {
        boolean hayCoincidencias = false;

        System.out.println("=== Pacientes para la fecha " + fechaBuscada + " ===");

        for (Orden o : turnosDelDia) {
            String fechaTurno = o.getFechaHora().substring(0, 10);
            if (fechaTurno.equals(fechaBuscada)) {
                hayCoincidencias = true;
                System.out.println("--- Paciente ---");
                System.out.printf("DNI:           %s%n", o.getDniPaciente());
                System.out.printf("Prioridad:     %d%n", o.getPrioridad());
                System.out.printf("Fecha y Hora:  %s%n", o.getFechaHora());
                System.out.println("--------------");
            }
        }

        if (!hayCoincidencias) {
            System.out.println("No se encontraron pacientes para esa fecha.");
        }
    }
}
