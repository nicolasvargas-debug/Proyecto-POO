package Excepciones;

public class UsuarioInvalido extends RuntimeException {
    public UsuarioInvalido() {
        super("El nombre de usuario no es valido");
    }
    public UsuarioInvalido(String mensaje) {
        super(mensaje);
    }
}
