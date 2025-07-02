package Logica;

import Entidades.Orden;
import TDA.*;
import Entidades.Consulta;
import Entidades.Paciente;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestorRecepcion {
    private Diccionario<String, Paciente> pacientes = new Diccionario<>();
    private Map<String, Cola<Orden>> turnosGeneralesPorEspecialidad = new HashMap<>();
    private Map<String, ColaConPrioridad<Orden>> urgenciasPorEspecialidad = new HashMap<>();
    private ListaEnlazada<Orden> turnosDelDia = new ListaEnlazada<>(); //creada para poder visualizar todos los turnos del dia, es decir, urgentes y generales

    public GestorRecepcion() {
        String[] especialidades = {"clinica", "pediatria", "traumatologia"};
        for (String esp : especialidades) {
            turnosGeneralesPorEspecialidad.put(esp, new Cola<>());
            urgenciasPorEspecialidad.put(esp, new ColaConPrioridad<>());
        }
    }


    public void registrarPaciente(String dni, String nombre, String apellido, LocalDate fechaNacimiento) {
        if (!dni.matches("\\d{7,8}")) {
            System.out.println("DNI inválido");
            return;
        }

        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+") || !apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            System.out.println("Nombre o apellido inválido");
            return;
        }

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        if (edad < 0 || edad > 99) {
            System.out.println("Edad inválida (calculada desde la fecha de nacimiento)");
            return;
        }

        if (pacientes.contieneClave(dni)) {
            System.out.println("Ya existe un paciente con ese DNI.");
        } else {
            Paciente p = new Paciente(dni, nombre, apellido, fechaNacimiento);
            pacientes.put(dni, p);
            System.out.println("Paciente " + nombre + " " + apellido + " registrado correctamente.");
        }
    }


    public void registrarUrgencia(String dni, String especialidad, String motivo, String diagnostico, int prioridad) {
        if (!pacientes.contieneClave(dni)) {
            System.out.println("Paciente no registrado.");
            return;
        }
        if (!urgenciasPorEspecialidad.containsKey(especialidad)) {
            System.out.println("Especialidad inválida.");
            return;
        }
        if (prioridad < 1 || prioridad > 5) {
            System.out.println("Prioridad inválida. Debe ser entre 1 y 5.");
            return;
        }
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String fechaHora = ahora.format(formatter);

        Orden orden = new Orden(dni, fechaHora, prioridad);
        urgenciasPorEspecialidad.get(especialidad).encolar(orden, prioridad);
        turnosDelDia.agregar(orden);

        Consulta consulta = new Consulta(fechaHora, motivo, diagnostico);
        pacientes.get(dni).agregarConsulta(consulta);

        System.out.println("Consulta urgente registrada en " + especialidad + " con prioridad " + prioridad);
    }



    public boolean existePaciente(String dni) {

        return pacientes.contieneClave(dni);
    }


    public void atenderPaciente(String especialidad) {
        if (!urgenciasPorEspecialidad.containsKey(especialidad) || !turnosGeneralesPorEspecialidad.containsKey(especialidad)) {
            System.out.println("Especialidad inválida.");
            return;
        }

        Orden ordenAtendida;
        if (!urgenciasPorEspecialidad.get(especialidad).estaVacia()) {
            ordenAtendida = urgenciasPorEspecialidad.get(especialidad).desencolar();
        } else {
            ordenAtendida = turnosGeneralesPorEspecialidad.get(especialidad).desencolar();
        }

        if (ordenAtendida != null) {
            Paciente p = pacientes.get(ordenAtendida.getDniPaciente());
            System.out.println("Atendiendo a: " + p.getNombre() + " " + p.getApellido() + " (Especialidad: " + especialidad + ")");
        } else {
            System.out.println("No hay pacientes en espera para " + especialidad);
        }
    }


    public void mostrarPacientes() {
        for (String dni : pacientes.claves()) {
            System.out.println(pacientes.get(dni));
        }
    }

    public void mostrarHistorial(String dni) {
    if (pacientes.contieneClave(dni)) {
        Paciente paciente = pacientes.get(dni);
        if (paciente.historialVacio()) {
            System.out.println("Historial vacío");
        } else {
            for (Consulta c : paciente.getHistorial()) {
                System.out.println(c);
                }
            }
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    public String verProximoTurnoUrgente(String especialidad) {
        if (!urgenciasPorEspecialidad.containsKey(especialidad)) {
            return "Especialidad inválida.";
        }
        ColaConPrioridad<Orden> cola = urgenciasPorEspecialidad.get(especialidad);
        if (!cola.estaVacia()) {
            return cola.verPrimero().toString();
        }
        return "Sin turnos urgentes en " + especialidad;
    }

    public String verProximoTurnoNormal(String especialidad) {
        if (!turnosGeneralesPorEspecialidad.containsKey(especialidad)) {
            return "Especialidad inválida.";
        }
        Cola<Orden> cola = turnosGeneralesPorEspecialidad.get(especialidad);
        if (!cola.estaVacia()) {
            return cola.verPrimero().toString();
        }
        return "Sin turnos normales en " + especialidad;
    }

    public Orden verProximaOrden(String especialidad) {
        if (!urgenciasPorEspecialidad.containsKey(especialidad)) {
            System.out.println("Especialidad inválida.");
            return null;
        }

        ColaConPrioridad<Orden> urgencias = urgenciasPorEspecialidad.get(especialidad);
        Cola<Orden> generales = turnosGeneralesPorEspecialidad.get(especialidad);

        if (!urgencias.estaVacia()) {
            return urgencias.verPrimero();
        } else if (!generales.estaVacia()) {
            return generales.verPrimero();
        } else {
            return null;
        }
    }

    public Paciente getPacientePorDni(String dni) {
    return pacientes.get(dni);
}

    public void mostrarTurnosDelDia() {
        for (Orden t : turnosDelDia) {
            System.out.println(t);
        }
    }
}
