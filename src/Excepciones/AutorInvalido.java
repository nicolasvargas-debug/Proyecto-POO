package Excepciones;

public class AutorInvalido extends RuntimeException {
    public AutorInvalido() {
        super("El autor no es valido");
    }
    public AutorInvalido(String mensaje) {
        super(mensaje);
    }
}
