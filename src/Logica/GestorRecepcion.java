package Logica;

import Entidades.Orden;
import TDA.*;
import Entidades.Consulta;
import Entidades.Paciente;

public class GestorRecepcion {
    private Diccionario<String, Paciente> pacientes = new Diccionario<>();
    private Cola<Orden> colaGeneral = new Cola<>();
    private ColaConPrioridad<Orden> colaUrgencias = new ColaConPrioridad<>();
    private ListaEnlazada<Orden> turnosDelDia = new ListaEnlazada<>(); //creada para poder visualizar todos los turnos del dia, es decir, urgentes y generales

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
            System.out.println("Paciente "+ nombre + " " + apellido + " registrado correctamente.");
        } else {
            System.out.println("Ya existe un paciente con ese DNI.");
        }
    }


    public void registrarUrgencia(String nombre, String apellido, String dni, String fechaHora, int prioridad) {
        if (!pacientes.contieneClave(dni)) {
            System.out.println("Paciente no registrado. No se puede asignar turno.");
            return;
    }

    if (prioridad < 1 || prioridad > 5) {
        System.out.println("Error: la prioridad debe estar entre 1 (no urgente) y 5 (emergencia vital).");
        return;
    }

    Orden t = new Orden(nombre, apellido, dni, fechaHora, prioridad);

        if (prioridad >= 3) {
            colaUrgencias.encolar(t, prioridad);
        } else {
        colaGeneral.encolar(t);
    }

    turnosDelDia.agregarOrdenado(t); // solo se agrega una vez a la lista del día

    System.out.println("Orden registrada para: " + nombre + " " + apellido + " → " + fechaHora + " | Prioridad " + prioridad);
}

    public void actualizarPrioridadesColaGeneral() {   // Mejora agregada para constatar el tiempo que lleva el paciente.
        Cola<Orden> nuevaColaGeneral = new Cola<>();
        long ahora = System.currentTimeMillis();
        long mediaHora = 30 * 60 * 1000;

        while (!colaGeneral.estaVacia()) {
            Orden orden = colaGeneral.desencolar();
            long tiempoEspera = ahora - orden.getTimestamp();

        if (tiempoEspera >= mediaHora) {
            int nuevaPrioridad = Math.min(orden.getPrioridad() + 1, 5);
            orden.setPrioridad(nuevaPrioridad);
            colaUrgencias.encolar(orden, nuevaPrioridad);
            Paciente p = pacientes.get(orden.getDniPaciente());
            System.out.println("Prioridad aumentada y movido a urgencias: " +
                p.getNombre() + " " + p.getApellido() + " → prioridad: " + nuevaPrioridad);
        } else {
            nuevaColaGeneral.encolar(orden);
        }
    }

    colaGeneral = nuevaColaGeneral;
}



    public boolean existePaciente(String dni) {

        return pacientes.contieneClave(dni);
    }


    public void atenderPaciente(String motivo, String diagnostico, String fecha) {
        actualizarPrioridadesColaGeneral();

        Orden turnoAtendido = !colaUrgencias.estaVacia()
            ? colaUrgencias.desencolar() 
            : colaGeneral.desencolar();
        if (turnoAtendido != null) {
            Paciente p = pacientes.get(turnoAtendido.getDniPaciente());
            System.out.println("Atendiendo a: " + p.getNombre() + " " + p.getApellido());
            p.agregarConsulta(new Consulta(fecha, motivo, diagnostico));
        }else {
                System.out.println("No hay pacientes en espera.");
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

    public String verProximoTurnoUrgente() {
        if (!colaUrgencias.estaVacia()) {
            Orden t = colaUrgencias.verPrimero();
            return t.toString();
        }
        return "Sin turnos";
    }

    public String verProximoTurnoNormal() {
        if (!colaGeneral.estaVacia()) {
            Orden t = colaGeneral.verPrimero();
            return t.toString();
        }
        return "Sin turnos";
    }

    public Orden verProximaOrden() {
        if (!colaUrgencias.estaVacia()) {
            return colaUrgencias.verPrimero();
        } else if (!colaGeneral.estaVacia()) {
            return colaGeneral.verPrimero();
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
