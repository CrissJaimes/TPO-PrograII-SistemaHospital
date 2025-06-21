package TDA;

public class ColaConPrioridad<T> {

    private static class Nodo<T> {
        T dato;
        int prioridad; // valores entre 1 (baja) y 5 (alta)
        Nodo<T> siguiente;

        Nodo(T dato, int prioridad) {
            this.dato = dato;
            this.prioridad = prioridad;
        }
    }

    private Nodo<T> primero;

    public void encolar(T elemento, int prioridad) {
        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }

        Nodo<T> nuevo = new Nodo<>(elemento, prioridad);

        if (estaVacia() || prioridad > primero.prioridad) {
            nuevo.siguiente = primero;
            primero = nuevo;
            return;
        }

        Nodo<T> actual = primero;
        while (actual.siguiente != null && actual.siguiente.prioridad >= prioridad) {
            actual = actual.siguiente;
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }


    public T desencolar() {
        if (estaVacia()) return null;
        T dato = primero.dato;
        primero = primero.siguiente;
        return dato;
    }

    public T verPrimero() {
        return estaVacia() ? null : primero.dato;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}
