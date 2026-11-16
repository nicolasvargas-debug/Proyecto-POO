package Excepciones;

public class PrestamoDuplicado extends RuntimeException {
    public PrestamoDuplicado() {
        super("El usuario ya tiene un prestamo de este libro");
    }
    public PrestamoDuplicado(String mensaje) {
        super(mensaje);
    }
}
