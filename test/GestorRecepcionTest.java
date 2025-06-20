import Logica.GestorRecepcion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorRecepcionTest {

    private GestorRecepcion sistema;

    @BeforeEach
    public void setUp() {
        sistema = new GestorRecepcion();
    }

    @Test
    public void testRegistrarPaciente() {
        assertFalse(sistema.existePaciente("12345678"));
        sistema.registrarPaciente("12345678", "Ana Gómez", 34);
        assertTrue(sistema.existePaciente("12345678"));
    }

    @Test
    public void testAgendarTurnoNormal() {
        sistema.registrarPaciente("12345678", "Ana Gómez", 34);
        sistema.agendarTurno("12345678", "2025-06-01 10:00");

        String turnoAgendado = sistema.verProximoTurnoNormal();
        assertNotNull(turnoAgendado);
        assertTrue(turnoAgendado.contains("12345678"));
        assertTrue(turnoAgendado.contains("10:00"));
    }

    @Test
    public void testRegistrarUrgenciaConPrioridad() {
        sistema.registrarPaciente("11111111", "Urgente Uno", 40);
        sistema.registrarPaciente("22222222", "Normal Dos", 50);

        sistema.agendarTurno("22222222", "2025-06-01 10:30");
        sistema.registrarUrgencia("11111111", "2025-06-01 10:00");

        String primero = sistema.verProximoTurnoUrgente();
        assertTrue(primero.contains("11111111")); // urgencia debe ir primero

        sistema.atenderPaciente("Motivo", "Diagnóstico", "2025-06-01");
        String siguiente = sistema.verProximoTurnoUrgente();
        assertTrue(siguiente.contains("22222222"));
}
}