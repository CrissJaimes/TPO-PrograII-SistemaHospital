package Logica;

import TDA.*;
import Entidades.Consulta;
import Entidades.Paciente;
import Entidades.Turno;

public class GestorRecepcion {
    private Diccionario<String, Paciente> pacientes = new Diccionario<>();
    private Cola<Turno> colaGeneral = new Cola<>();
    private ColaConPrioridad<Turno> colaUrgencias = new ColaConPrioridad<>();
    private ListaEnlazada<Turno> turnosDelDia = new ListaEnlazada<>(); //creada para poder visualizar todos los turnos del dia, es decir, urgentes y generales

    public void registrarPaciente(String dni, String nombre, String apellido, int edad) throws IllegalArgumentException {
        if (!dni.matches("\\d{7,8}")) {
            System.out.println("DNI inválido");
            return;
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            System.out.println("Nombre inválido");
            return;
        }
        if (edad < 0 || edad > 99) {
            System.out.println("Edad inválida");
            return;
        }
        if (!pacientes.contieneClave(dni)) {
            pacientes.put(dni, new Paciente(dni, nombre, apellido, edad));
            System.out.println("Paciente registrado correctamente.");
        } else {
            System.out.println("Ya existe un paciente con ese DNI.");
        }
    }


    public void agendarTurno(String dni, String fechaHora) {
        if (pacientes.contieneClave(dni)) {
            Turno t = new Turno(dni, fechaHora, false);
            colaGeneral.encolar(t);
            turnosDelDia.agregar(t);
        }
    }

    public void registrarUrgencia(String dni, String fechaHora) {
        if (pacientes.contieneClave(dni)) {
            Turno t = new Turno(dni, fechaHora, true);
             colaUrgencias.encolar(t, true); // true = prioridad
            turnosDelDia.agregar(t);
        }
    }

    public boolean existePaciente(String dni) {

        return pacientes.contieneClave(dni);
    }


    public void atenderPaciente(String motivo, String diagnostico, String fecha) {
        Turno turnoAtendido = !colaUrgencias.estaVacia() 
            ? colaUrgencias.desencolar() 
            : colaGeneral.desencolar();
        if (turnoAtendido != null) {
            Paciente p = pacientes.get(turnoAtendido.getDniPaciente());
            p.agregarConsulta(new Consulta(fecha, motivo, diagnostico));
        }
    }

    public void mostrarPacientes() {
        for (String dni : pacientes.claves()) {
            System.out.println(pacientes.get(dni));
        }
    }

    public void mostrarHistorial(String dni) {
        if (pacientes.contieneClave(dni)) {
            for (Consulta c : pacientes.get(dni).getHistorial()) {
                System.out.println(c);
            }
        }
    }

    public String verProximoTurnoUrgente() {
        if (!colaUrgencias.estaVacia()) {
            Turno t = colaUrgencias.verPrimero();
            return t.toString();
        }
        return "Sin turnos";
    }

    public String verProximoTurnoNormal() {
        if (!colaGeneral.estaVacia()) {
            Turno t = colaGeneral.verPrimero();
            return t.toString();
        }
        return "Sin turnos";
    }

    public void mostrarTurnosDelDia() {
        for (Turno t : turnosDelDia) {
            System.out.println(t);
        }
    }
}
