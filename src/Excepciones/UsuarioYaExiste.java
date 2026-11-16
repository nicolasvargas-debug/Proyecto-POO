package Excepciones;

public class UsuarioYaExiste extends RuntimeException {
    public UsuarioYaExiste() {
        super("Ya existe un usuario con ese nombre");
    }
    public UsuarioYaExiste(String mensaje) {
        super(mensaje);
    }
}
