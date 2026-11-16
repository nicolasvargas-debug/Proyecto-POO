package Excepciones;

public class UltimoAdministrador extends RuntimeException {
    public UltimoAdministrador() {
        super("No se puede eliminar al ultimo administrador");
    }
    public UltimoAdministrador(String mensaje) {
        super(mensaje);
    }
}
