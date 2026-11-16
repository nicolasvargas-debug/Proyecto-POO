package Excepciones;

public class LimitePrestamos extends RuntimeException {
    public LimitePrestamos() {
        super("El usuario ya alcanzo el maximo de 3 entre prestamos y reservas");
    }
    public LimitePrestamos(String mensaje) {
        super(mensaje);
    }
}
