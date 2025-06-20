package test;

import TDA.Cola;

public class TestCola{

	public static void main(String[] args) {
		Cola aux = new Cola();
		aux.encolar(4);
		aux.encolar(2);
		aux.encolar(1);
		aux.encolar(6);
		while(!aux.estaVacia()) {
			System.out.println("Primero " + aux.verPrimero());
			aux.desencolar();
		}
		System.out.println("Cola Vacia " + aux.estaVacia());
	}

}
