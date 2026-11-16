package Excepciones;

public class LibroConReservas extends RuntimeException {
    public LibroConReservas() {
        super("No se puede eliminar un libro con reservas");
    }
    public LibroConReservas(String mensaje) {
        super(mensaje);
    }
}
