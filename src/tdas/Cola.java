package tdas;

public class Cola<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> primero;
    private Nodo<T> ultimo;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }

    public void encolar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = primero.dato;
        primero = primero.siguiente;
        if (primero == null) {
            ultimo = null;
        }
        return dato;
    }

    public T verPrimero() {
        return estaVacia() ? null : primero.dato;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}

