package Excepciones;

public class FotoInvalida extends RuntimeException {
    public FotoInvalida() {
        super("El archivo seleccionado no es una imagen valida");
    }
    public FotoInvalida(String mensaje) {
        super(mensaje);
    }
}
