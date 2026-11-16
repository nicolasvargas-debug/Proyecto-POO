package Excepciones;

public class LibroConPrestamos extends RuntimeException {
    public LibroConPrestamos() {
        super("No se puede eliminar un libro con prestamos vigentes");
    }
    public LibroConPrestamos(String mensaje) {
        super(mensaje);
    }
}
