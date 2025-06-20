package test;

import TDA.ColaConPrioridad;

public class TestColaConPrioridad {
	public static void main(String[] args) 	{
	ColaConPrioridad<String> cola = new ColaConPrioridad<>();

	cola.encolar("Paciente A", 2); // menos urgente
	cola.encolar("Paciente B", 5); // emergencia vital
	cola.encolar("Paciente C", 3); // urgencia

	System.out.println(cola.desencolar()); // Paciente B
	System.out.println(cola.desencolar()); // Paciente C
	System.out.println(cola.desencolar()); // Paciente A

	}
}
