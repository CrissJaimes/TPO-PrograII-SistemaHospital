package Entidades;

import TDA.Pila;

public class Paciente {
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private Pila<Consulta> historial;

    public Paciente(String dni, String nombre, String apellido, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.historial = new Pila<>();
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void agregarConsulta(Consulta consulta) {
        historial.apilar(consulta);
    }

    public Iterable<Consulta> getHistorial() {
        return historial;
    }

    @Override
    public String toString() {
        return nombre + " (DNI: " + dni + ", Edad: " + edad + ")";
    }
}
