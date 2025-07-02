package TDA;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Entidades.Orden;

public class ListaEnlazada<T> implements Iterable<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> cabeza;
    private Nodo<T> cola;

    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;

            public boolean hasNext() {
                return actual != null;
            }

            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.dato;
                actual = actual.siguiente;
                return dato;
            }
        };
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarOrdenado(Orden nuevaOrden) {
        Nodo<T> nuevo = new Nodo<>((T) nuevaOrden);
        if (cabeza == null || ((Orden) cabeza.dato).getFechaHora().compareTo(nuevaOrden.getFechaHora()) > 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        if (cola == null) cola = nuevo;
            return;
    }

        Nodo<T> actual = cabeza;
        while (actual.siguiente != null &&
            ((Orden) actual.siguiente.dato).getFechaHora().compareTo(nuevaOrden.getFechaHora()) <= 0) {
            actual = actual.siguiente;
    }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        if (nuevo.siguiente == null) {
            cola = nuevo;
        }
    }

}

