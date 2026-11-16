package Excepciones;

public class CopiasInvalidas extends RuntimeException {
    public CopiasInvalidas() {
        super("La cantidad de copias no es valida");
    }
    public CopiasInvalidas(String mensaje) {
        super(mensaje);
    }
}
