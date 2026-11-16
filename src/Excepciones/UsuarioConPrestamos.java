package Excepciones;

public class UsuarioConPrestamos extends RuntimeException {
    public UsuarioConPrestamos() {
        super("No se puede eliminar un usuario con prestamos vigentes");
    }
    public UsuarioConPrestamos(String mensaje) {
        super(mensaje);
    }
}
