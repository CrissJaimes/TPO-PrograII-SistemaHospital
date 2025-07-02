package Entidades;

public class Orden implements Comparable<Orden> {
    private String dniPaciente;
    private String fechaHora;
    private int prioridad;

    public Orden(String dniPaciente, String fechaHora, int prioridad) {

        this.dniPaciente = dniPaciente;
        this.fechaHora = fechaHora;
        this.prioridad = prioridad;
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

    @Override
    public int compareTo(Orden otro) {

        return Integer.compare(otro.prioridad, this.prioridad);
    }

    @Override
    public String toString() {
        return "El paciente con DNI: " + dniPaciente + " con prioridad: " + prioridad;
    }
}
