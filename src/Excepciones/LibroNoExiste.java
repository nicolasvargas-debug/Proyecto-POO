package Excepciones;

public class LibroNoExiste extends RuntimeException {
    public LibroNoExiste() {
        super("No existe ningun libro con ese ISBN");
    }
    public LibroNoExiste(String mensaje) {
        super(mensaje);
    }
}
