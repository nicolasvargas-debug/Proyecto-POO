package Excepciones;

public class IsbnInvalido extends RuntimeException {
    public IsbnInvalido() {
        super("El ISBN no es valido");
    }
    public IsbnInvalido(String mensaje) {
        super(mensaje);
    }
}
