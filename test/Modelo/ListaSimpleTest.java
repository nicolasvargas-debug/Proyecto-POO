package Modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListaSimpleTest {
	private ListaSimple<String> lista;
	
	@BeforeEach 
	void setUp() {
		lista = new ListaSimple<String>();
	}
	@Test
	void testAgregar() {
		assertTrue(lista.estaVacia());
		lista.agregar("Libro 1");
		assertEquals(1, lista.getTamanio());
		assertFalse(lista.estaVacia());
	}
	
	@Test
	void testEliminar() {
		lista.agregar("Inicio");
        lista.agregar("Medio");
        lista.agregar("Final");

        assertTrue(lista.eliminar("Medio"));
        assertEquals(2, lista.getTamanio());

        assertTrue(lista.eliminar("Inicio"));
        assertEquals(1, lista.getTamanio());

        assertTrue(lista.eliminar("Final"));
        assertEquals(0, lista.getTamanio());
        assertTrue(lista.estaVacia());
    }
	
	@Test
	void testEstaVcia() {
		assertTrue(lista.estaVacia());
		assertEquals(0, lista.getTamanio());
		assertFalse(lista.eliminar("libro q no existe"));
	}
}
