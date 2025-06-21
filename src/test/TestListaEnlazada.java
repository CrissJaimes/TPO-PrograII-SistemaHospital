package test;

import TDA.ListaEnlazada;
import java.util.Iterator;


    public class TestListaEnlazada {
        public static void main(String[] args) {
            ListaEnlazada<String> lista = new ListaEnlazada<>();

            System.out.println("== Test 1: Lista vacía ==");
            System.out.println("¿Está vacía? " + (lista.estaVacia() ? "Sí" : "No")); // Esperado: Sí

            System.out.println("\n== Test 2: Agregar un elemento ==");
            lista.agregar("Hola");
            System.out.println("¿Está vacía? " + (lista.estaVacia() ? "Sí" : "No")); // Esperado: No

            System.out.println("\n== Test 3: Agregar varios elementos ==");
            lista.agregar("Mundo");
            lista.agregar("!");
            System.out.print("Elementos en orden: ");
            for (String s : lista) {
                System.out.print(s + " ");
            }
            System.out.println(); // Esperado: Hola Mundo !

            System.out.println("\n== Test 4: Recorrer con iterador manual ==");
            Iterator<String> it = lista.iterator();
            while (it.hasNext()) {
                System.out.println("Elemento: " + it.next());
            }

            System.out.println("\n== Test 5: Excepción al hacer next() sin elementos ==");
            try {
                it.next(); // ya no hay elementos
                System.out.println("ERROR: No lanzó excepción esperada");
            } catch (Exception e) {
                System.out.println("Excepción capturada correctamente: " + e.getClass().getSimpleName());
            }
        }
    }
