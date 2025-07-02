package Entidades;

import TDA.Pila;

import java.time.LocalDate;
import java.time.Period;

public class Paciente {
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Pila<Consulta> historial;

    public Paciente(String dni, String nombre, String apellido, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.historial = new Pila<>();
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int calcularEdad() { return Period.between(fechaNacimiento, LocalDate.now()).getYears();}

    public void agregarConsulta(Consulta consulta) {
        historial.apilar(consulta);
    }

    public Iterable<Consulta> getHistorial() {
        return historial;
    }

    public boolean historialVacio() {
        return historial.estaVacia();
    }

    @Override
    public String toString() {
        return nombre + " " +apellido + " (DNI: " + dni + ", Edad: " + fechaNacimiento + ")";
    }
}
