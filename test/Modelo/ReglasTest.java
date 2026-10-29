package Modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Excepciones.RenovacionConFila;
import Excepciones.RenovacionDoble;
import Excepciones.SinCopias;

public class ReglasTest {
	private Usuario usuario;
	private Libro libro1;
	private Libro libro2;
	private Libro libro3;
	private Libro libro4;
	
	@BeforeEach
	void setUp() {
		usuario = new Usuario("Samuel", "Luque", "Vegetta777", "Guapo123", 777);
        libro1 = new Libro("111", "Libro 1", "Autor 1", 2);
        libro2 = new Libro("222", "Libro 2", "Autor 2", 2);
        libro3 = new Libro("333", "Libro 3", "Autor 3", 2);
        libro4 = new Libro("444", "Libro 4", "Autor 4", 2);
	}
	
	@Test
	void maximoTresLibros() {
		assertTrue(usuario.agregarPrestamo(libro1));
		assertTrue(usuario.agregarPrestamo(libro2));
		assertTrue(usuario.agregarPrestamo(libro3));
		assertFalse(usuario.agregarPrestamo(libro4));
		
		assertEquals(3, usuario.getPretamos().getTamanio());
	}
	
	@Test
	void usuarioBloqueado() {
		usuario.bloquear();
        assertTrue(usuario.isBloqueado());
        
        assertThrows(IllegalStateException.class, () -> {usuario.agregarPrestamo(libro1);});
    }
	@Test
    void renovarUnaVez() {
        assertThrows(RenovacionDoble.class, () -> {throw new RenovacionDoble("El préstamo ya fue renovado una vez.");});
    }
	
	@Test
	void NoRenovarSiHayFila() {
		assertThrows(RenovacionConFila.class, () -> {throw new RenovacionConFila("No se puede renovar porque hay personas en la fila de espera.");});
	}
	
	@Test
	void EntrasCuandoCopiasLleganACero() {
		Libro libro = new Libro("12345", "Clean Code", "Robert C. Martin", 1);
        libro.prestarLibro(); 
        assertEquals(0, libro.getCopiasDisponibles());
        
        assertThrows(SinCopias.class, () -> {throw new SinCopias("No hay copias disponibles para este libro.");});
	}
	@Test
    void DevolverConFila() {
        Libro libro = new Libro("12345", "Clean Code", "Robert C. Martin", 1);
        libro.prestarLibro();
        assertEquals(0, libro.getCopiasDisponibles());
        
        libro.devolverLibro();
        assertEquals(1, libro.getCopiasDisponibles());
    }
}
