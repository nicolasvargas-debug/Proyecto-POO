package Modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColaTest {

    private Caja<String> caja;

    @BeforeEach
    void setUp() {
        cola = new Cola<>();
    }

    @Test
    void testOrdenFIFO() {
        cola.encolar("Primero");
        cola.encolar("Segundo");
        cola.encolar("Tercero");

        assertEquals("Primero", cola.primero());
        assertEquals("Primero", cola.desencolar());
        assertEquals("Segundo", cola.desencolar());
        assertEquals("Tercero", cola.desencolar());
        assertTrue(cola.estaVacia());
    }

    @Test
    void testDesencolarEnColaVacia() {
        assertTrue(cola.estaVacia());
        assertThrows(Exception.class, () -> {
            cola.desencolar();
        });
    }

    @Test
    void testTamanio() {
        assertEquals(0, cola.getTamanio());
        cola.encolar("Elemento 1");
        assertEquals(1, cola.getTamanio());
        cola.encolar("Elemento 2");
        assertEquals(2, cola.getTamanio());
        cola.desencolar();
        assertEquals(1, cola.getTamanio());
    }
}