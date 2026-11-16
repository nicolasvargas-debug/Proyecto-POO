package Excepciones;

public class SinCopias extends RuntimeException {
    public SinCopias() {
        super("El libro no tiene copias disponibles");
    }
    public SinCopias(String mensaje) {
        super(mensaje);
    }
}
