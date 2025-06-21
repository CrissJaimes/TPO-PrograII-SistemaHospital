package test;

import TDA.Pila;

public class TestPila {

	public static void main(String[] args) {
	
		Pila pila = new Pila();
		System.out.println("Pila inicializada");
		pila.apilar(1);
		pila.apilar(2);
		pila.apilar(3);
		pila.apilar(4);
		pila.apilar(5);
		
		
		
		System.out.println("Pila cargada");
		
		System.out.println("tope: " + pila.verTope());
		
		while (!pila.estaVacia()) {
			System.out.println(pila.verTope());
			pila.desapilar();
		}
	}

}
