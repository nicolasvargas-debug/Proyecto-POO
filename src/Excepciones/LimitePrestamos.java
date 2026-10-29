package Excepciones;

public class LimitePrestamos extends RuntimeException {
    public LimitePrestamos(String mensaje) {
        super(mensaje);
    }
}