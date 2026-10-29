package Excepciones;

public class RenovacionDoble extends RuntimeException {
    public RenovacionDoble(String mensaje) {
        super(mensaje);
    }
}