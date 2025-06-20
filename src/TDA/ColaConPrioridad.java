package TDA;

public class ColaConPrioridad<T> {

    private static class Nodo<T> {
        T dato;
        boolean prioridad;
        Nodo<T> siguiente;

        Nodo(T dato, boolean prioridad) {
            this.dato = dato;
            this.prioridad = prioridad;
        }
    }

    private Nodo<T> primero;
    private Nodo<T> ultimo;

    public void encolar(T elemento, boolean esPrioritario) {
        Nodo<T> nuevo = new Nodo<>(elemento, esPrioritario);

        if (estaVacia()) {
            primero = ultimo = nuevo;
            return;
        }

        if (esPrioritario) {
            nuevo.siguiente = primero;
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T dato = primero.dato;
        primero = primero.siguiente;
        if (primero == null) ultimo = null;
        return dato;
    }

    public T verPrimero() {
        return estaVacia() ? null : primero.dato;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}

