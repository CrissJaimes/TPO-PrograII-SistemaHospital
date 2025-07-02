package Entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Orden implements Comparable<Orden> {
    private String nombre;
    private String apellido;
    private String dniPaciente;
    private String fechaHora;
    private int prioridad;
    private long timestamp; // guarda el tiempo en milisegundos


    public Orden(String nombre, String apellido, String dniPaciente, String fechaHora, int prioridad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dniPaciente = dniPaciente;
        this.fechaHora = fechaHora;
        this.prioridad = prioridad;
        this.timestamp = System.currentTimeMillis(); // se guarda el tiempo actual de la orden
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public int prioridad() {
        return prioridad;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int nuevaPrioridad) {
        this.prioridad = nuevaPrioridad;
    }


    @Override
    public int compareTo(Orden otro) {

        return Integer.compare(otro.prioridad, this.prioridad);
    }

    public LocalDateTime getFechaHoraComoLocalDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(this.fechaHora, formatter);
}

    public boolean esAnteriorA(Orden otra) {
        return this.getFechaHoraComoLocalDateTime().isBefore(otra.getFechaHoraComoLocalDateTime());
    }

    @Override
    public String toString() {
        return "El paciente " + nombre + " " + apellido +
               " - DNI: " + dniPaciente +
               " | Prioridad: " + prioridad +
               " | Turno: " + fechaHora;
    }

}
