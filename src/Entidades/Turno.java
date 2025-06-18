package Entidades;


public class Turno implements Comparable<Turno> {
    private String dniPaciente;
    private String fechaHora;
    private boolean esUrgente;

    public Turno(String dniPaciente, String fechaHora, boolean esUrgente) {
        this.dniPaciente = dniPaciente;
        this.fechaHora = fechaHora;
        this.esUrgente = esUrgente;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public boolean esUrgente() {
        return esUrgente;
    }

    @Override
    public int compareTo(Turno otro) {

        return Boolean.compare(otro.esUrgente, this.esUrgente);
    }

    @Override
    public String toString() {
        return fechaHora + " - DNI: " + dniPaciente + (esUrgente ? " (URGENTE)" : "");
    }
}
