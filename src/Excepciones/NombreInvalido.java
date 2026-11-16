package Excepciones;

public class NombreInvalido extends RuntimeException {
    public NombreInvalido() {
        super("El nombre no es valido");
    }
    public NombreInvalido(String mensaje) {
        super(mensaje);
    }
}
