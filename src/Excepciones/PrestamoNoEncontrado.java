package Excepciones;

public class PrestamoNoEncontrado extends RuntimeException {
    public PrestamoNoEncontrado() {
        super("El usuario no tiene ningun prestamo de ese libro");
    }
    public PrestamoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
