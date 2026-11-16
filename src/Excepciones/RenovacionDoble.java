package Excepciones;

public class RenovacionDoble extends RuntimeException {
    public RenovacionDoble() {
        super("El prestamo ya fue renovado");
    }
    public RenovacionDoble(String mensaje) {
        super(mensaje);
    }
}
