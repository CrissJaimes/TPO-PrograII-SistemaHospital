package test;

import Entidades.Orden;
import TDA.ListaEnlazada;
import java.util.Scanner;

public class TestAgregarOrdenado {
    public static void main(String[] args) {
        ListaEnlazada<Orden> turnosDelDia = new ListaEnlazada<>();

        // Turnos desordenados
        turnosDelDia.agregarOrdenado(new Orden("12345678", "2025-07-06 14:00", 3));
        turnosDelDia.agregarOrdenado(new Orden("87654321", "2025-07-12 09:15", 2));
        turnosDelDia.agregarOrdenado(new Orden("11112222", "2025-07-06 11:30", 5));
        turnosDelDia.agregarOrdenado(new Orden("22223333", "2025-07-06 08:45", 4));
        turnosDelDia.agregarOrdenado(new Orden("33334444", "2025-07-09 16:00", 2));
        turnosDelDia.agregarOrdenado(new Orden("44445555", "2025-06-23 10:00", 1));
        turnosDelDia.agregarOrdenado(new Orden("55556666", "2025-07-05 12:15", 3));
        turnosDelDia.agregarOrdenado(new Orden("66667777", "2025-07-06 13:30", 5));

        // Creamos el tester con los turnos
        TurnoTester tester = new TurnoTester(turnosDelDia);

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese una fecha (yyyy-MM-dd) o escriba 'todos': ");
        String entrada = sc.nextLine().trim();

        if (entrada.equalsIgnoreCase("todos") || entrada.isEmpty()) {
            tester.mostrarTurnosDelDia();
        } else {
            tester.mostrarTurnosPorFecha(entrada);
        }

        sc.close();
    }
}