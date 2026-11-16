package Excepciones;

public class UsuarioBloqueado extends RuntimeException {
    public UsuarioBloqueado() {
        super("El usuario esta bloqueado");
    }
    public UsuarioBloqueado(String mensaje) {
        super(mensaje);
    }
}
