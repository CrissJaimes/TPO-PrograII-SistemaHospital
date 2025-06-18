package tdas;

import java.util.LinkedList;

public class Diccionario<K, V> {

    private static class Par<K, V> {
        K clave;
        V valor;

        Par(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private LinkedList<Par<K, V>>[] tabla;
    private int capacidad = 16;

    @SuppressWarnings("unchecked")
    public Diccionario() {
        tabla = new LinkedList[capacidad];
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(K clave) {
        return Math.abs(clave.hashCode()) % capacidad;
    }

    public void put(K clave, V valor) {
        int indice = hash(clave);
        for (Par<K, V> par : tabla[indice]) {
            if (par.clave.equals(clave)) {
                par.valor = valor;
                return;
            }
        }
        tabla[indice].add(new Par<>(clave, valor));
    }
    public Iterable<K> claves() {
        ListaEnlazada<K> lista = new ListaEnlazada<>();
        for (LinkedList<Par<K, V>> bucket : tabla) {
            for (Par<K, V> par : bucket) {
            lista.agregar(par.clave);
            }
        }
        return lista;
    }

    public V get(K clave) {
        int indice = hash(clave);
        for (Par<K, V> par : tabla[indice]) {
            if (par.clave.equals(clave)) {
                return par.valor;
            }
        }
        return null;
    }

    public boolean contieneClave(K clave) {
        return get(clave) != null;
    }

    public void eliminar(K clave) {
        int indice = hash(clave);
        tabla[indice].removeIf(par -> par.clave.equals(clave));
    }
}
