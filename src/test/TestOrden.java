package test;

import java.time.format.DateTimeFormatter;

import Entidades.Orden;
import TDA.ListaEnlazada;

public class TestOrden {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ListaEnlazada<Orden> turnosDelDia = new ListaEnlazada<>();

        // Agregá a mano algunas órdenes desordenadas
        Orden o1 = new Orden("12345678", "2025-07-06 14:00", 3);
        Orden o2 = new Orden("87654321", "2025-07-12 09:15", 2);
        Orden o3 = new Orden("11112222", "2025-07-06 11:30", 5);
        Orden o4 = new Orden("22223333", "2025-07-06 08:45", 4);
        Orden o5 = new Orden("33334444", "2025-07-09 16:00", 2);
        Orden o6 = new Orden("44445555", "2025-06-23 10:00", 1);
        Orden o7 = new Orden("55556666", "2025-07-05 12:15", 3);
        Orden o8 = new Orden("66667777", "2025-07-06 13:30", 5);

        turnosDelDia.agregarOrdenado(o1);
        turnosDelDia.agregarOrdenado(o2);
        turnosDelDia.agregarOrdenado(o3);
        turnosDelDia.agregarOrdenado(o4);
        turnosDelDia.agregarOrdenado(o5);
        turnosDelDia.agregarOrdenado(o6);
        turnosDelDia.agregarOrdenado(o7);
        turnosDelDia.agregarOrdenado(o8);

        // Mostrar resultados
        for (Orden o : turnosDelDia) {
            System.out.println("DNI: " + o.getDniPaciente()
                             + " | Prioridad: " + o.getPrioridad()
                             + " | Turno: " + o.getFechaHora());
        }
    }
}
