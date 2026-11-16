package Excepciones;

public class LibroYaExiste extends RuntimeException {
    public LibroYaExiste() {
        super("Ya existe un libro con ese ISBN");
    }
    public LibroYaExiste(String mensaje) {
        super(mensaje);
    }
}
