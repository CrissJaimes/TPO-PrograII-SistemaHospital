package Entidades;

public class Turno implements Comparable<Turno> {
    private String nombre;
    private String apellido;
    private String dniPaciente;
    private String fechaHora;
    private int prioridad;

    public Turno(String nombre, String apellido, String dniPaciente, String fechaHora, int prioridad) {
        this.nombre = nombre;
        this.apellido = apellido;
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
    public int compareTo(Turno otro) {

        return Integer.compare(otro.prioridad, this.prioridad);
    }

    @Override
    public String toString() {
        return "El paciente " + nombre + " " + apellido + " - DNI: " + dniPaciente + " con prioridad: " + prioridad ;
    }
}
