package Entidades;


public class Consulta {
    private String fecha;
    private String motivo;
    private String diagnostico;

    public Consulta(String fecha, String motivo, String diagnostico) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    @Override
    public String toString() {
        return fecha + " - Motivo: " + motivo + " - Diagnóstico: " + diagnostico;
    }
}
